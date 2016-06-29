/**
 * Copyright (c) 2016, Geanina Mihalea <geanina.mihalea@gmail.com>.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
     *
     * @param c the context
     * @param table the table with the data to be saved
     */
    public static void saveFile(final Component c, final JTable table, final String suggestedFileName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(suggestedFileName));
        if (fileChooser.showSaveDialog(c) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            exportToExcel(table, file.getAbsolutePath());
        }
    }

    /**
     * Exports a JTable to Excel file.
     *
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
