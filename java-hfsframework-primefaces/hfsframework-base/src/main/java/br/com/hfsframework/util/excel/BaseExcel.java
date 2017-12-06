/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.excel;

import java.util.Iterator;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The Class BaseExcel.
 */
public abstract class BaseExcel {

	/** The meses. */
	public String[] meses = { "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO",
			"OUTUBRO", "NOVEMBRO", "DEZEMBRO", "TOTAL" };
	
	/** The s coluna. */
	public String[] sColuna = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * Cria o border grossa.
	 *
	 * @param wb
	 *            the wb
	 * @param linha
	 *            the linha
	 * @param colunaInicial
	 *            the coluna inicial
	 * @param colunaFinal
	 *            the coluna final
	 */
	protected void createBorderGrossa(XSSFWorkbook wb, XSSFRow linha, int colunaInicial, int colunaFinal) {
		XSSFCell cell;
		CellStyle cellStyle;

		cell = linha.getCell(colunaInicial);
		cellStyle = cell.getCellStyle();
		cellStyle.setBorderLeft(BorderStyle.THICK);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.THICK);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cell.setCellStyle(cellStyle);

		for (int i = colunaInicial + 1; i <= colunaFinal; i++) {
			cell = linha.createCell(i);
			cellStyle = wb.createCellStyle();
			cellStyle.setBorderTop(BorderStyle.THICK);
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderBottom(BorderStyle.THICK);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cell.setCellStyle(cellStyle);
		}

		cell = linha.getCell(colunaFinal);
		cellStyle = cell.getCellStyle();
		cellStyle.setBorderRight(BorderStyle.THICK);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cell.setCellStyle(cellStyle);
	}

	/**
	 * Cria o cor borda grossa.
	 *
	 * @param r
	 *            the r
	 * @param g
	 *            the g
	 * @param b
	 *            the b
	 * @param borda
	 *            the borda
	 * @param cellStyle
	 *            the cell style
	 */
	protected void createCorBordaGrossa(int r, int g, int b, boolean borda, CellStyle cellStyle) {
		XSSFColor color = new XSSFColor(new java.awt.Color(r, g, b));
		((XSSFCellStyle) cellStyle).setFillForegroundColor(color);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		if (borda) {
			cellStyle.setBorderTop(BorderStyle.THICK);
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderLeft(BorderStyle.THICK);
			cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderBottom(BorderStyle.THICK);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderRight(BorderStyle.THICK);
			cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		}
	}

	/**
	 * Cria o cor borda fina.
	 *
	 * @param r
	 *            the r
	 * @param g
	 *            the g
	 * @param b
	 *            the b
	 * @param borda
	 *            the borda
	 * @param cellStyle
	 *            the cell style
	 */
	protected void createCorBordaFina(int r, int g, int b, boolean borda, CellStyle cellStyle) {
		XSSFColor color = new XSSFColor(new java.awt.Color(r, g, b));
		((XSSFCellStyle) cellStyle).setFillForegroundColor(color);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		if (borda) {
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		}
	}

	/**
	 * Cria o cell.
	 *
	 * @param wb
	 *            the wb
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param texto
	 *            the texto
	 * @param r
	 *            the r
	 * @param g
	 *            the g
	 * @param b
	 *            the b
	 * @param borda
	 *            the borda
	 */
	protected void createCell(XSSFWorkbook wb, XSSFRow row, int column, String texto, int r, int g, int b, boolean borda) {
		Font fonte = wb.createFont();
		fonte.setFontName("Arial");
		fonte.setFontHeightInPoints((short) 12);
		fonte.setBold(true);
		XSSFCell cell = row.createCell(column);
		CellStyle cellStyle = wb.createCellStyle();
		cell.setCellType(CellType.STRING);
		//CreationHelper ch = wb.getCreationHelper();
		//cell.setCellValue(ch.createRichTextString(texto));
		cell.setCellValue(texto);
		cellStyle.setFont(fonte);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);

		createCorBordaGrossa(r, g, b, borda, cellStyle);

		cell.setCellStyle(cellStyle);
	}

	/**
	 * Cria o cell 2.
	 *
	 * @param wb
	 *            the wb
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param texto
	 *            the texto
	 * @param r
	 *            the r
	 * @param g
	 *            the g
	 * @param b
	 *            the b
	 * @param borda
	 *            the borda
	 */
	protected void createCell2(XSSFWorkbook wb, XSSFRow row, int column, String texto, int r, int g, int b,
			boolean borda) {
		Font fonte = wb.createFont();
		fonte.setFontName("Bookman Old Style");
		fonte.setFontHeightInPoints((short) 10);
		fonte.setBold(true);
		XSSFCell cell = row.createCell(column);
		CellStyle cellStyle = wb.createCellStyle();
		cell.setCellType(CellType.STRING);
		//CreationHelper ch = wb.getCreationHelper();
		//cell.setCellValue(ch.createRichTextString(texto));
		cell.setCellValue(texto);
		cellStyle.setFont(fonte);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);

		createCorBordaGrossa(r, g, b, borda, cellStyle);

		cell.setCellStyle(cellStyle);
	}

	/**
	 * Cria o cell.
	 *
	 * @param wb
	 *            the wb
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param texto
	 *            the texto
	 * @param r
	 *            the r
	 * @param g
	 *            the g
	 * @param b
	 *            the b
	 * @return the XSSF cell
	 */
	protected XSSFCell createCell(XSSFWorkbook wb, XSSFRow row, int column, String texto, int r, int g, int b) {
		Font fonte = wb.createFont();
		fonte.setFontName("Bookman Old Style");
		fonte.setFontHeightInPoints((short) 10);
		fonte.setBold(true);		
		XSSFCell cell = row.createCell(column);
		CellStyle cellStyle = wb.createCellStyle();
		if (texto.substring(0, 1).equals("=")) {
			cell.setCellType(CellType.FORMULA);
			cell.setCellFormula(texto.substring(1));
			cellStyle.setDataFormat((short)0x28);
		} else if (NumberUtils.isNumber(texto)) {
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Double.parseDouble(texto));
			cellStyle.setDataFormat((short)0x28);
		} else {
			cell.setCellType(CellType.STRING);
			//CreationHelper ch = wb.getCreationHelper();
			//cell.setCellValue(ch.createRichTextString(texto));
			cell.setCellValue(texto);
		}

		cellStyle.setFont(fonte);

		createCorBordaGrossa(r, g, b, true, cellStyle);

		cell.setCellStyle(cellStyle);
		
		return cell;
	}

	/**
	 * Cria o cell.
	 *
	 * @param wb
	 *            the wb
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param texto
	 *            the texto
	 * @param sNomeFonte
	 *            the s nome fonte
	 * @param bCentralizar
	 *            the b centralizar
	 * @param nFormatoCelula
	 *            the n formato celula
	 * @param tamanhoFonte
	 *            the tamanho fonte
	 */
	protected void createCell(XSSFWorkbook wb, XSSFRow row, int column, String texto, String sNomeFonte,
			boolean bCentralizar, short nFormatoCelula, short tamanhoFonte) {
		Font fonte = wb.createFont();
		fonte.setFontName(sNomeFonte);
		fonte.setFontHeightInPoints(tamanhoFonte);
		fonte.setBold(true);
		XSSFCell cell = row.createCell(column);
		CellStyle cellStyle = wb.createCellStyle();
		if (texto.substring(0, 1).equals("=")) {
			cell.setCellType(CellType.FORMULA);
			cell.setCellFormula(texto.substring(1));
			cellStyle.setDataFormat(nFormatoCelula);
		} else if (NumberUtils.isNumber(texto)) {
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Double.parseDouble(texto));
			cellStyle.setDataFormat(nFormatoCelula);
		} else {
			cell.setCellType(CellType.STRING);
			//CreationHelper ch = wb.getCreationHelper();
			//cell.setCellValue(ch.createRichTextString(texto));
			cell.setCellValue(texto);
		}

		cellStyle.setFont(fonte);

		if (bCentralizar) {
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
		}

		createCorBordaFina(255, 255, 255, true, cellStyle);

		cell.setCellStyle(cellStyle);
	}

	/**
	 * Cria o cell.
	 *
	 * @param wb
	 *            the wb
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param texto
	 *            the texto
	 * @param sNomeFonte
	 *            the s nome fonte
	 * @param bCentralizar
	 *            the b centralizar
	 * @param nFormatoCelula
	 *            the n formato celula
	 */
	protected void createCell(XSSFWorkbook wb, XSSFRow row, int column, String texto, String sNomeFonte,
			boolean bCentralizar, short nFormatoCelula) {
		createCell(wb, row, column, texto, sNomeFonte, bCentralizar, nFormatoCelula, (short)10);
	}
	
	/**
	 * Cria o cell.
	 *
	 * @param wb
	 *            the wb
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param texto
	 *            the texto
	 * @param sNomeFonte
	 *            the s nome fonte
	 * @param bCentralizar
	 *            the b centralizar
	 */
	protected void createCell(XSSFWorkbook wb, XSSFRow row, int column, String texto, String sNomeFonte,
			boolean bCentralizar) {
		createCell(wb, row, column, texto, sNomeFonte, bCentralizar, (short)0x28, (short)10);
	}	
	
	/**
	 * Cria o cell.
	 *
	 * @param wb
	 *            the wb
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param texto
	 *            the texto
	 */
	protected void createCell(XSSFWorkbook wb, XSSFRow row, int column, String texto) {
		createCell(wb, row, column, texto, "Bookman Old Style", false);
	}

	/**
	 * Auto size columns.
	 *
	 * @param workbook
	 *            the workbook
	 */
	public void autoSizeColumns(Workbook workbook) {
		int columnIndex;
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			if (sheet.getPhysicalNumberOfRows() > 0) {
				Row row = sheet.getRow(0);
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					columnIndex = cell.getColumnIndex();
					sheet.autoSizeColumn(columnIndex, true);

					if (sheet.getColumnWidth(columnIndex) < 4200){
						sheet.setColumnWidth(columnIndex, 4200);
					}
				}
			}
		}
	}
	
	/**
	 * Atribui o tamanho coluna padrao.
	 *
	 * @param workbook
	 *            the workbook
	 * @param nomePlanilha
	 *            the nome planilha
	 * @param linha
	 *            the linha
	 * @param tamanho
	 *            the tamanho
	 */
	public void setTamanhoColunaPadrao(Workbook workbook, String nomePlanilha, int linha, int tamanho) {
		int columnIndex;
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			if (sheet.getSheetName().equals(nomePlanilha)) {
				if (sheet.getPhysicalNumberOfRows() > 0) {
					Row row = sheet.getRow(linha);
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						columnIndex = cell.getColumnIndex();
						sheet.setColumnWidth(columnIndex, tamanho);
					}
					break;
				}
			}
		}
	}
	
}
