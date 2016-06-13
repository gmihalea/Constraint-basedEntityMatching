package client.actions;

import client.util.ViewConstants;
import org.apache.poi.hssf.usermodel.*;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.*;

/**
 * Save File Action Class
 */
public class ExportDataAction {

    /**
     * Action for Download Results Button
     * @param c the context
     * @param table the table with the data to be saved
     */
    public static void saveFile(final Component c, final JTable table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(ViewConstants.SUGGESTED_FILE_NAME));
        if (fileChooser.showSaveDialog(c) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            exportToExcel(table, file.getAbsolutePath());
        }
    }

    /**
     * Exports a JTable to Excel file.
     * @param table table to export
     * @param path the path to the excel file
     */
    public static void exportToExcel(final JTable table, final String path) {
        try {
            HSSFWorkbook fWorkbook = new HSSFWorkbook();
            HSSFSheet fSheet = fWorkbook.createSheet(ViewConstants.MATCHING_RESULTS);
            HSSFFont sheetTitleFont = fWorkbook.createFont();
            File file = new File(path);
            HSSFCellStyle cellStyle = fWorkbook.createCellStyle();

            sheetTitleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            TableModel model = table.getModel();

            TableColumnModel tcm = table.getColumnModel();
            HSSFRow fRow1 = fSheet.createRow((short) 0);

            for(int j = 0; j < tcm.getColumnCount(); j++) {
                HSSFCell cell = fRow1.createCell((short) j);
                cell.setCellValue(tcm.getColumn(j).getHeaderValue().toString());
            }

            for (int i = 1; i < model.getRowCount(); i++) {
                HSSFRow fRow = fSheet.createRow((short) i);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    HSSFCell cell = fRow.createCell((short) j);
                    cell.setCellValue(model.getValueAt(i, j).toString());
                    cell.setCellStyle(cellStyle);
                }
            }
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            fWorkbook.write(bos);
            bos.close();
            fileOutputStream.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
