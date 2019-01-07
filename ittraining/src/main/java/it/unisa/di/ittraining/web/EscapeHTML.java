package it.unisa.di.ittraining.web;

public class EscapeHTML {

	public static String escapeHtml(String testo) {
		testo = testo.replace("À", "&Agrave;");
		testo = testo.replace("Á", "&Aacute;");
		testo = testo.replace("Â", "&Acirc;");
		testo = testo.replace("Ã", "&Atilde;");
		testo = testo.replace("Ä", "&Auml;");
		testo = testo.replace("Å", "&Aring;");
		testo = testo.replace("Æ", "&AElig;");
		testo = testo.replace("Ç", "&Ccedil;");
		testo = testo.replace("È", "&Egrave;");
		testo = testo.replace("É", "&Eacute;");
		testo = testo.replace("Ê", "&Ecirc;");
		testo = testo.replace("Ë", "&Euml;");
		testo = testo.replace("Ì", "&Igrave;");
		testo = testo.replace("Í", "&Iacute;");
		testo = testo.replace("Î", "&Icirc;");
		testo = testo.replace("Ï", "&Iuml;");
		testo = testo.replace("Ñ", "&Ntilde;");
		testo = testo.replace("Ò", "&Ograve;");
		testo = testo.replace("Ó", "&Oacute;");
		testo = testo.replace("Ô", "&Ocirc;");
		testo = testo.replace("Õ", "&Otilde;");
		testo = testo.replace("Ö", "&Ouml;");
		testo = testo.replace("Ø", "&Oslash;");
		testo = testo.replace("Ù", "&Ugrave;");
		testo = testo.replace("Ú", "&Uacute;");
		testo = testo.replace("Û", "&Ucirc;");
		testo = testo.replace("Ü", "&Uuml;");
		testo = testo.replace("ß", "&szlig");
		testo = testo.replace("à", "&agrave;");
		testo = testo.replace("á", "&aacute;");
		testo = testo.replace("â", "&acirc;");
		testo = testo.replace("ã", "&atilde;");
		testo = testo.replace("ä", "&auml;");
		testo = testo.replace("å", "&aring;");
		testo = testo.replace("æ", "&aelig;");
		testo = testo.replace("ç", "&ccedil;");
		testo = testo.replace("è", "&egrave;");
		testo = testo.replace("é", "&eacute;");
		testo = testo.replace("ê", "&ecirc;");
		testo = testo.replace("ë", "&euml;");
		testo = testo.replace("ì", "&igrave;");
		testo = testo.replace("í", "&iacute;");
		testo = testo.replace("î", "&icirc;");
		testo = testo.replace("ï", "&iuml;");
		testo = testo.replace("ñ", "&ntilde;");
		testo = testo.replace("ò", "&ograve;");
		testo = testo.replace("ó", "&oacute;");
		testo = testo.replace("ô", "&ocirc;");
		testo = testo.replace("œ", "&oelig;");
		testo = testo.replace("õ", "&otilde;");
		testo = testo.replace("ö", "&ouml;");
		testo = testo.replace("ø", "&oslash;");
		testo = testo.replace("ù", "&ugrave;");
		testo = testo.replace("ú", "&uacute;");
		testo = testo.replace("û", "&ucirc;");
		testo = testo.replace("ü", "&uuml;");
		testo = testo.replace("ÿ", "&yuml;");
		
		
		return testo;
	}
}
