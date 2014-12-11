package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.items.armor.Armor.Glyph;
import com.dit599.customPD.items.armor.glyphs.*;

public abstract class GlyphsMapping {
	private static List<Class<? extends Glyph>> glyphclasslist = null;
	private static List<String> glyphnamelist = null;

	
	public static void glyphMappingInit() {
		glyphclasslist = new ArrayList<Class<? extends Glyph>>();
		glyphclasslist.add(Affection.class);
		glyphclasslist.add(AntiEntropy.class);
		glyphclasslist.add(Bounce.class);
		glyphclasslist.add(Displacement.class);
		glyphclasslist.add(Entanglement.class);
		glyphclasslist.add(Metabolism.class);
		glyphclasslist.add(Multiplicity.class);
		glyphclasslist.add(Potential.class);
		glyphclasslist.add(Stench.class);
		glyphclasslist.add(Viscosity.class);

		glyphnamelist = new ArrayList<String>();
		glyphnamelist.add("Affection");
		glyphnamelist.add("Anti-Entropy");
		glyphnamelist.add("Bounce");
		glyphnamelist.add("Displacement");
		glyphnamelist.add("Entanglement");
		glyphnamelist.add("Metabolism");
		glyphnamelist.add("Multiplicity");
		glyphnamelist.add("Potential");
		glyphnamelist.add("Stench");
		glyphnamelist.add("Viscosity");
	}

	public static String getGlyphName(Class<? extends Glyph> glyph) {
		for(int i=0;i<glyphclasslist.size();i++)
		 {
			if(glyphclasslist.get(i).getName().equals(glyph.getName()))
			{
				return glyphnamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Glyph> getGlyphClass(String glyphname) {
		for(int i=0;i<glyphnamelist.size();i++)
		 {
			if(glyphnamelist.get(i).equals(glyphname))
			{
				return glyphclasslist.get(i);
			}
		 }
		return null;
	}
	public static List<String> getAllNames()
	{
		return glyphnamelist;
		
	}
}
