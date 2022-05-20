package utilities;

import org.apache.commons.codec.digest.DigestUtils;
import org.testng.Reporter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_256;

public class TestHelper {

    public static List<String> extractUrls(String input) {
        List<String> result = new ArrayList<String>();
        Pattern pattern = Pattern.compile(
                "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
                        "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
                        "|mil|biz|info|mobi|name|aero|jobs|museum" +
                        "|travel|[a-z]{2}))(:[\\d]{1,5})?" +
                        "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                        "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                        "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                        "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

    public static void downloadFileFromURL(String url, String filePath) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            Reporter.log(String.format("Can't download file from %s", url) + e, true);
        }
    }

    public static void deleteTempFile() {
        File file = new File(Config.getProperty("/TempFile"));
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public static boolean compareFilesByHash(String file1, String file2) {
        String file1Hash = "init1";
        String file2Hash = "init2";
        try {
            file1Hash = new DigestUtils(SHA_256).digestAsHex(new File(file1));
            file2Hash = new DigestUtils(SHA_256).digestAsHex(new File(file2));
        } catch (IOException e) {
            Reporter.log("Cannot calculate hash " + e, true);
        }
        return file1Hash.equals(file2Hash);
    }
}
