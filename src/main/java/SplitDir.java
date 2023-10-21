import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.List;

public class SplitDir {

    public static void main(String[] args) {
        String dir = "D:\\Media\\mi6\\Camera\\";
//        String dir = "D:\\Media\\iPhone8p\\相机胶卷\\";
//        String dir = "D:\\Media\\iPhone8p\\重复文件\\";
        List<String> fileList = FileUtil.listFileNames(dir);
        System.out.println("共 " + fileList.size() + " 个文件");
        int i = 0;
        int dirNo = 1;
        File src;
        File target;
        FileUtil.mkdir(String.valueOf(dirNo));
        for (String file : fileList) {
            src = new File(dir + file);
            target = new File(dir + dirNo + "\\" + file);
            FileUtil.move(src, target, true);
            i++;
            if (i == 500) {
                FileUtil.mkdir(String.valueOf(dirNo));
                dirNo++;
                i = 0;
            }
        }
    }

}
