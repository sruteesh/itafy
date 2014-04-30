package utils.fileUpdater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import utils.feedReader.FeedReader;
import utils.fileWriter.ArffWriter;

/**
 * This class was only created to solve this situation. </br>
 * While development, the <code>FeedReader</code> was getting some improvements while generating
 * the <em>arff</em> files each day. So we had smth like:
 * 
 * <pre>
 *  |:-----:|:----------------|--------------------:|
 *  | day 1 | FeedParser V. a | file with version a |
 *  | day 2 | FeedParser V. b | file with version b |
 *  | ...   |  ...            |       ...           |
 *  | day n | FeedParser V. N | file with version n |
 * </pre>
 * 
 * This class is able to take one arff file and improve it to the latest version acording to
 * <code>FeedReader</code> version
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see FeedReader
 */
public class FileUpdater {
	private ArffWriter writer;

	/**
	 * The main loop code (I mean the "for each line in the document") is duplicated code, but
	 * when this project was done, it was using Java7 which doesn't support <em>lambda</em> arguments
	 * (passing behaviour to a function); very cool smth like Ruby
	 * <code>file.forEachLine { |line| doSmthCoolToTheLine(line) }</code>
	 * 
	 * @param path
	 * @return success
	 */
	public boolean updateFile(String path) {
		System.out.println(path);
		String outPath = calculateOutPath(path);
		writer = new ArffWriter(outPath);
		boolean fail = false;
		try {
			FileReader fileReader = new FileReader(new File(path));
			BufferedReader br = new BufferedReader(fileReader);
			String line = null;
			// if no more lines readLine() returns null
			while ((line = br.readLine()) != null) {
				fail |= updateLine(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			fail = true;
		}
		return !fail;
	}

	/**
	 * @param path example <code>abc/dfg/.../22_2_22.arff</code>
	 * @return example <code>abc/dfg/.../22_2_22_revised.arff</code>
	 */
	private String calculateOutPath(String path) {
		String response = "";
		// get everything until the '.'
		response = StringUtils.substringBefore(path, ".");
		response += "_revised.arff";
		return response;
	}

	/**
	 * Calls <code>updateFile()</code> for each file in the folder
	 * 
	 * @param folder
	 * @return success
	 * @see Stackoverflow #1844688
	 */
	public boolean updateFiles(final File folder) {
		boolean fail = false;
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				updateFiles(fileEntry);
			} else {
				if (isNormalFile(fileEntry)) {
					fail |= updateFile(fileEntry.getPath());
				}
			}
		}
		return !fail;
	}

	/** Checks that the file will be readable... and isn't a hidden file such as <em>.DS_STORE</em> files */
	private boolean isNormalFile(File fileEntry) {
		return fileEntry.canRead() && fileEntry.canWrite() && !fileEntry.isHidden();
	}

	/** @see Stackoverflow #4662215 */
	private boolean updateLine(String line) {
		boolean response;
		if (isContent(line)) {
			System.out.println("==> " + line); //XXX
			String content = getContentToNormalize(line);
			content = utils.helpers.StringHelper.normalize(content);
			String classAsString = getClassValue(line);
			response = writer.writeData(content, classAsString);
		} else {
			response = writer.writeText(line);
		}
		return response;
	}

	private boolean isContent(String s) {
		return !ArffWriter.isEmpty(s) && !ArffWriter.isDeclaration(s) && !ArffWriter.isComment(s);
	}

	/**
	 * @param s input String, for example <code>"aaa...zzz,CULTURA"</code>
	 * @return aaa...zzz
	 */
	private String getContentToNormalize(String s) {
		return s.substring(1, s.lastIndexOf(',') - 1);
	}

	/**
	 * @param s input String, for example <code>".......,CULTURA"</code>
	 * @return CULTURA
	 */
	private String getClassValue(String s) {
		return s.substring(s.lastIndexOf(',') + 1);
	}

}
