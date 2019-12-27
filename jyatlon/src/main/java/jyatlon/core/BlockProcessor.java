package jyatlon.core;

import java.io.Writer;

import jyatlon.core.Block.PathBlock;
import jyatlon.core.Block.ValueBlock;
import jyatlon.core.Struct.Section;
import jyatlon.core.Struct.Template;

/**
 * @author linte
 * SRP: A state less processor that generate a text file.
 *
 */
public class BlockProcessor {

	public static void merge(PathBlock pb, Writer w, Object r) {
		
		extractValues(pb, r);
	}
	private static void extractValues(PathBlock pb, Object root) {
		
		pb.getValueBlocks();
		
		//String valueArg, String aliasName, List<Operation> operation
		
		
//		public List<List<?>> getValues(Path path) throws Exception{ // runtime
//		List<List<?>> results = new ArrayList<List<?>>();
////		int index = 0;
//		for (ValueCall c : calls) {
////			if (results.isEmpty())
////				results.add(c.getValues(null, root));
////			else {
//				List<List<?>> newResults = new ArrayList<List<?>>();
//				for (List<?> previousResult : results) {
//					List <?> currentValues = c.getValues(previousResult.get(index), root);
//					for (Object o2 : currentValues) {
//						List<Object> newResult = new ArrayList<Object>(previousResult);
//						newResult.add(o2);
//						newResults.add(newResult);
//					}
//				}
//				results = newResults;
//				index++;
////			}
//		}
//		return results;
	}
	private static void merge(ValueBlock vb, Writer w) {
		// Compute all values
		
		


		
	}
	
}
