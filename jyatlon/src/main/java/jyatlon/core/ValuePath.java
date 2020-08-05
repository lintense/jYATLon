package jyatlon.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author linte
 * SRP: Encapsulates all the path mechanisms
 */
public class ValuePath {

	// This is a naive implementation
	public final String[] aliases;
	public final String[] classes;
	public final Object[] objects;
	
	public ValuePath(String pathName, String pathAlias, Object obj) {
		this.aliases = new String[] {pathAlias};
		this.classes = new String[] {pathName};
		this.objects = new Object[] {obj};
	}
	protected ValuePath(String[] classes, String[] aliases, Object[] objects) {
		assert classes.length == aliases.length && aliases.length == objects.length;
		// Classes and objects arrays must match
		assert IntStream.range(1, objects.length).filter(i -> objects[i].getClass().getSimpleName().equals(classes[i])).count() == objects.length - 1;
		// Alias names and classes names should not intersect
		assert !Stream.of(classes).filter(c -> new HashSet<>(Arrays.asList(aliases)).contains(c)).findAny().isPresent();
		
		this.aliases = aliases;
		this.classes = classes;
		this.objects = objects;
	}
	public ValuePath add(String className, String pathAlias, Object obj) {
		assert className != null && pathAlias != null;
		return new ValuePath(
				Stream.of(Arrays.asList(this.classes), Arrays.asList(className)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.classes.length + 1]),
				Stream.of(Arrays.asList(this.aliases), Arrays.asList(pathAlias)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.aliases.length + 1]),
				Stream.of(Arrays.asList(this.objects), Arrays.asList(obj)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new Object[this.objects.length + 1])
			);
	}
	public ValuePath add(ValuePath p) {
		return new ValuePath(
				Stream.of(Arrays.asList(this.classes), Arrays.asList(p.classes)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.classes.length + p.classes.length]),
				Stream.of(Arrays.asList(this.aliases), Arrays.asList(p.aliases)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.aliases.length + p.aliases.length]),
				Stream.of(Arrays.asList(this.objects), Arrays.asList(p.objects)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new Object[this.objects.length + p.objects.length])
			);
	}
	public Object getObject() {
		return objects[objects.length - 1];
	}
	public Object getObjectForName(String name) {
		for (int i = classes.length; i > 0; i--)
			if (name.equals(classes[i-1]) || name.equals(aliases[i-1]))
				return objects[i-1];
		throw new IllegalArgumentException("The name '" + name + "' cannot be found in the path");
	}
	public String getAliasName() {
		return aliases[aliases.length - 1];
	}
	public String getClassName() {
		return classes[classes.length - 1];
	}
	public boolean containsClassName(String className) {
		return Arrays.stream(this.classes).anyMatch(x -> className.equals(x));
	}
	public boolean containsAliasName(String alias) {
		return Arrays.stream(this.aliases).anyMatch(x -> alias.equals(x));
	}
}
