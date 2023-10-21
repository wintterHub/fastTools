import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuChong {

    public static void main(String[] args) {
//		String dir = "D:\\i8p\\相机胶卷\\";
//		String dir_ = "D:\\i8p\\重复文件\\";

        String dir = "D:\\Media\\iPhone8p\\相机胶卷\\";
        String dir_ = "D:\\Media\\iPhone8p\\重复文件\\";

//				String dir = "D:\\Media\\i8p\\相机胶卷\\";
//				String dir_ = "D:\\Media\\i8p\\重复文件\\";

        List<String> fileList = FileUtil.listFileNames(dir);
        List<String> fileIMG_List = new ArrayList<>();
        List<String> fileShuZiList = new ArrayList<>();
        System.out.println("共 " + fileList.size() + " 个文件");
        int i = 1;
        for (String file : fileList) {
            System.out.println(file + " 文件分类中，当前第" + i + "条");
            if (file.startsWith("IMG_")) {
                fileIMG_List.add(file);
            } else {
                fileShuZiList.add(file);
            }
            i++;
        }

        Map<String, String> IMG_FilePap = new HashMap<>();
        System.out.println("IMG_开头的文件共 " + fileIMG_List.size() + " 个");
        int j = 1;
        for (String file : fileIMG_List) {
            System.out.println("正在计算文件 " + file + " 的MD5，当前第 " + j + " 条");
            IMG_FilePap.put(DigestUtil.md5Hex(new File(dir + file)), file);
            j++;
        }

        System.out.println("开始比较差异，共 " + fileShuZiList.size() + " 个文件需要比较");
        int k = 1;
        for (String file : fileShuZiList) {
            System.out.println("正在查找相同 ----- " + file + " 当前第" + k + "条");
            String s = DigestUtil.md5Hex(new File(dir + file));
            if (IMG_FilePap.containsKey(s)) {
                FileUtil.move(new File(dir + file), new File(dir_ + file), true);
                System.out.println("文件 " + file + " ----- " + s + " 已移动到" + dir_);
            }
            k++;
        }

    }

}
