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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class UtilisateurPDFExporter {

	private List<Utilisateur> listutilisateur;

	public UtilisateurPDFExporter(List<Utilisateur> listutilisateur) {
		super();
		this.listutilisateur = listutilisateur;
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.RED);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		//font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("id", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("email", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("mot de passe", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("nom de l'utilisateur", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("accès", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("role", font));
		table.addCell(cell);
		
		
	}
	
	private void writeTableData(PdfPTable table) {
		for (Utilisateur u : listutilisateur) {
			table.addCell(String.valueOf(u.getId()));
			table.addCell(String.valueOf(u.getEmail()));
			table.addCell(String.valueOf(u.getPassword()));
			table.addCell(String.valueOf(u.getUsername()));
			table.addCell(String.valueOf(u.isValid()));
			table.addCell(String.valueOf(u.getRole().getNom()));
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		document.add(new Paragraph("liste de tout les utilisateurs"));
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		
		document.close();
	}
}
