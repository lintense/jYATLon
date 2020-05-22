package jyatlon.core;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author linte
 * SRP: Hide the implementation of the path mechanism
 */
public class ValuePath {

//	private static final String SEPARATOR = File.separator;
//	private static final String X = Utils.unquote(YATLParser.VOCABULARY.getLiteralName(YATLParser.PATHSEP));
//	private static final String ANYPATH = Utils.unquote(YATLParser.VOCABULARY.getLiteralName(YATLParser.ANYPATH));
	
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
		assert !Stream.of(classes).filter(c -> Set.of(aliases).contains(c)).findAny().isPresent();
		
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
	public String getAlias() {
		return aliases[aliases.length - 1];
	}
	public Object getValueForName(String valueArg) { // TODO not used
		// Aliases have precedence over classes because we can choose them!
		OptionalInt opt1 = IntStream.range(0, aliases.length).filter(i -> aliases[i].equals(valueArg)).findAny();
		if (opt1.isPresent())
			return objects[opt1.getAsInt()];
		OptionalInt opt2 = IntStream.range(0, classes.length).filter(i -> classes[i].equals(valueArg)).findAny();
		if (opt2.isPresent())
			return objects[opt1.getAsInt()];
		
		throw new IllegalArgumentException("The name '" + valueArg + "' does not exist in the current scope.");
	}
//	public String getPathName() {
//		return String.join(ValuePath.SEPARATOR, classes);
//	}
// The following is not good because duplication is allowed is the objects are the same
//	public boolean hasDuplicatedAliases() {
//		Set<String> alreadyFound = new HashSet<String>();
//		for (String alias : aliases)
//			if (alreadyFound.contains(alias))
//				return true;
//			else
//				alreadyFound.add(alias);
//		return false;
//	}
//	public int getLastAliasIndex(String alias){
//		for (int i = aliases.length; i > 0; i--)
//			if (alias.equals(aliases[i-1]))
//				return i-1;
//		return -1;
//	}

	

//	public static class ValuePath extends Path {
//
//		public ValuePath(String pathName, String pathAlias, Object obj) {
//			super(pathName, pathAlias, obj);
//			// TODO Auto-generated constructor stub
//		}
//		
//	}
}
