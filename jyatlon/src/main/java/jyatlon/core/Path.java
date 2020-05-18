package jyatlon.core;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import jyatlon.generated.YATLParser;

/**
 * @author linte
 * SRP: Hide the implementation of the path mechanism
 */
public class Path {

	private static final String SEPARATOR = File.separator;
	private static final String ANYPATH = YATLParser.VOCABULARY.getLiteralName(YATLParser.ANYPATH).replace("'", "");
	
	// This is a naive implementation
	public final String[] aliases;
	public final String[] classes;
	public final Object[] objects;
	
	public Path(String pathName, String pathAlias, Object obj) {
		this.aliases = new String[] {pathAlias};
		this.classes = new String[] {pathName};
		this.objects = new Object[] {obj};
	}
	protected Path(String[] classes, String[] aliases, Object[] objects) {
		assert classes.length == aliases.length && aliases.length == objects.length;
		// Classes and objects arrays must match
		assert IntStream.range(1, objects.length).filter(i -> objects[i].getClass().getSimpleName().equals(classes[i])).count() == objects.length - 1;
		// Alias names and classes names should not intersect
		assert !Stream.of(classes).filter(c -> Set.of(aliases).contains(c)).findAny().isPresent();
		
		this.aliases = aliases;
		this.classes = classes;
		this.objects = objects;
	}
	public Path add(String className, String pathAlias, Object obj) {
		assert className != null && pathAlias != null;
		return new Path(
				Stream.of(Arrays.asList(this.classes), Arrays.asList(className)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.classes.length + 1]),
				Stream.of(Arrays.asList(this.aliases), Arrays.asList(pathAlias)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new String[this.aliases.length + 1]),
				Stream.of(Arrays.asList(this.objects), Arrays.asList(obj)).flatMap(x -> x.stream()).collect(Collectors.toList()).toArray(new Object[this.objects.length + 1])
			);
	}
	public int compatibility(List<String> path, boolean isAbsolute) {
		// FIXME - Add path Alias functionality
		int i1 = classes.length;
		int i2 = path.size();
		int compatibility = 0;
		while (i1 > 0 && i2 > 0) {
			String p1 = classes[--i1];
			String p2 = path.get(--i2);
			if (p1.equals(p2) || ANYPATH.equals(p2))
				compatibility++;
			else
				break;
		}
		return compatibility;
	}
	public Object getObject() {
		return objects[objects.length - 1];
	}
	public Object getValueForName(String valueArg) {
		// Aliases have precedence over classes because we can choose them!
		OptionalInt opt1 = IntStream.range(0, aliases.length).filter(i -> aliases[i].equals(valueArg)).findAny();
		if (opt1.isPresent())
			return objects[opt1.getAsInt()];
		OptionalInt opt2 = IntStream.range(0, classes.length).filter(i -> classes[i].equals(valueArg)).findAny();
		if (opt2.isPresent())
			return objects[opt1.getAsInt()];
		
		throw new IllegalArgumentException("The name '" + valueArg + "' does not exist in the current scope.");
	}
	public String getPathName() {
		return String.join(Path.SEPARATOR, classes);
	}
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
	public boolean canAddAliasObjectToMap(Map<String, Object> aliasObjects) {
		for (int i = 0; i < aliases.length; i++) {
			if (aliases[i] != null) {
				Object newObj = objects[i];
				Object previousObj = aliasObjects.put(aliases[i], newObj);
				if (previousObj != null && !Matcher.isSameObject(newObj, previousObj))
					return false;
			}
		}
		return true;
	}
	
	public static class CallPath extends Path {

		public CallPath(String pathName, String pathAlias, Object obj) {
			super(pathName, pathAlias, obj);
			// TODO Auto-generated constructor stub
		}
		
	}
	public static class ValuePath extends Path {

		public ValuePath(String pathName, String pathAlias, Object obj) {
			super(pathName, pathAlias, obj);
			// TODO Auto-generated constructor stub
		}
		
	}
}
