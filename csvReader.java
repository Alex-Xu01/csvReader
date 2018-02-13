// package csvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class csvReader {

    /**
     * 泛型嵌套list转换为二维数组
     *
     * @param src List<List<T>> 原嵌套list （子list的长度必须相等）
     * @return T[][] 转换后的二维数组
     */
    public static <T> T[][] listsToArrays(List<List<T>> src, Class<T> type) {
        if (src == null || src.isEmpty()) {
            return null;
        }
        // 初始化泛型二维数组
        T[][] dest = dest = (T[][]) Array.newInstance(type, src.size(), src.get(0).size());
        for (int i = 0; i < src.size(); i++) {
            for (int j = 0; j < src.get(i).size(); j++) {
                dest[i][j] = src.get(i).get(j);
            }
        }
        return dest;
    }

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * 获取真实文件路径
     */
    public static String getRealFilePath(String path) {
        path = path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
        if (path.endsWith(FILE_SEPARATOR)) {
            return path;
        } else {
            return path + FILE_SEPARATOR;
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\xulih\\Desktop\\csvReader\\src";
        String fileName = "test.csv";
        filePath = getRealFilePath(filePath);

        String realFilePath=filePath + fileName;
        File file = new File(realFilePath);
        List<List<String>> lines = new ArrayList<>();

        Scanner inputStream;

        try {
            inputStream = new Scanner(file);

            while (inputStream.hasNext()) {
                String line = inputStream.nextLine();
                String[] values = line.split(",");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        listsToArrays(lines, String.class);


    }


}
