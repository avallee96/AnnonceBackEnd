package com.intiformation.model;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

public class AnnoncePDFExporter {

	private List<Annonce> listannonce;

	public AnnoncePDFExporter(List<Annonce> listannonce) {
		this.listannonce = listannonce;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.RED);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		//font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("id", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("date d'expiration", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("date de publication", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("description", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("photo", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("titre", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("catégorie", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("utilisateur", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("validation"));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table) {
		for (Annonce a : listannonce) {
			table.addCell(String.valueOf(a.getId()));
			table.addCell(String.valueOf(a.getDate_exp()));
			table.addCell(String.valueOf(a.getDate_pub()));
			table.addCell(String.valueOf(a.getDescription()));
			table.addCell(String.valueOf(a.getPhoto()));
			table.addCell(String.valueOf(a.getTitre()));
			table.addCell(String.valueOf(a.getCategorie().getNom()));
			table.addCell(String.valueOf(a.getUtilisateur().getUsername()));
			table.addCell(String.valueOf(a.isValid()));
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		document.add(new Paragraph("liste de tout les annonces"));
		
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		
		document.close();
	}
	
	
}
