package cn.yznu.imssra.POI;

import cn.yznu.imssra.bean.Result;
import cn.yznu.imssra.bean.User;
import cn.yznu.imssra.service.ImssraService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static cn.yznu.imssra.util.constants.Constant.*;

/**
 * 操作生成Excel
 */
public class POIUtil {

    public static String generateDocx(List<Result> results, ImssraService imssraService, HttpServletRequest request) throws IOException {

        int currentRow = 0;//记录当前行
        FileOutputStream output = null;
        String bashPath = request.getSession().getServletContext().getRealPath("/upload");
        //创建一个Excel对象
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建表单Sheet对象
        XSSFSheet sheet = wb.createSheet();
        //创建Row对象
        XSSFRow row0 = sheet.createRow(currentRow);//表头
        currentRow++;

        //第一行
        XSSFCell cell0 =  row0.createCell(0);//编号
        XSSFCell cell1 =  row0.createCell(1);//成果名
        XSSFCell cell2 =  row0.createCell(2);//学院名
        XSSFCell cell3 =  row0.createCell(3);//大类
        XSSFCell cell4 =  row0.createCell(4);//小类
        XSSFCell cell5 =  row0.createCell(5);//简介
        XSSFCell cell6 =  row0.createCell(6);//审核状态
        XSSFCell cell7 =  row0.createCell(7);//是否优秀
        XSSFCell cell8 =  row0.createCell(8);//所属用户
        XSSFCell cell9 =  row0.createCell(9);//说明

        cell0.setCellValue("编号");
        cell1.setCellValue("成果名称");
        cell2.setCellValue("所属学院");
        cell3.setCellValue("所属一类");
        cell4.setCellValue("所属二类");
        cell5.setCellValue("成果简介");
        cell6.setCellValue("审核状态");
        cell7.setCellValue("是否优秀");
        cell8.setCellValue("所属用户");
        cell9.setCellValue("成果说明");

        for (Result result:results){
            XSSFRow row = sheet.createRow(currentRow);
            currentRow++;
            User user = imssraService.findUserById(result.getUserid());
            String realName = user.getRealName();
            if (realName == null || realName.equals("")){
                realName = "未知";
            }
            XSSFCell cell_0 =  row.createCell(0);//编号
            XSSFCell cell_1 =  row.createCell(1);//成果名
            XSSFCell cell_2 =  row.createCell(2);//学院名
            XSSFCell cell_3 =  row.createCell(3);//大类
            XSSFCell cell_4 =  row.createCell(4);//小类
            XSSFCell cell_5 =  row.createCell(5);//简介
            XSSFCell cell_6 =  row.createCell(6);//审核状态
            XSSFCell cell_7 =  row.createCell(7);//是否优秀
            XSSFCell cell_8 =  row.createCell(8);//所属用户
            XSSFCell cell_9 =  row.createCell(9);//说明

            cell_0.setCellValue(result.getId());
            cell_1.setCellValue(result.getResultname());
            cell_2.setCellValue(colleges[result.getCollegename()]);
            cell_3.setCellValue(type_big[result.getTypebig()]);
            cell_4.setCellValue(type_small[result.getTypesmall()]);
            cell_5.setCellValue(result.getDescription());
            cell_6.setCellValue(trail_state[result.getTrailstate()]);
            cell_7.setCellValue(isGoodResule[result.getIsgood()]);
            cell_8.setCellValue(realName);
            cell_9.setCellValue(result.getInstruction());

            File file = new File(bashPath, "info.xlsx");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //输出文件
            output = new FileOutputStream(bashPath+ File.separator+"info.xlsx");
            wb.write(output);
        }
        output.flush();
        output.close();
        return bashPath+ File.separator+"导出信息.xlsx";
    }
}
