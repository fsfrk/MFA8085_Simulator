import java.util.HashMap;
import java.util.Map;

public class Languages 
{
	public static byte langChoice = 0;									//0 = German, 1 = English
	public static Map<String[],Integer> terms = new HashMap<String[],Integer>();
	
	private void selectLanguage()
	{
		int i = 0;
		terms.put(new String[]{"Datei","File"},i++);
		terms.put(new String[]{"Beenden","Exit"},i++);
		terms.put(new String[]{"Extras","Extras"},i++);
		terms.put(new String[]{"Rechner","Calculator"},i++);
		terms.put(new String[]{"Befehlssatz","Commands"},i++);
		terms.put(new String[]{"Hilfe","Manual"},i++);
		terms.put(new String[]{"Ausführen","Execute"},i++);
		terms.put(new String[]{"Aus","Off"},i++);
		terms.put(new String[]{"An","On"},i++);
		terms.put(new String[]{"Schritt-Modus","Step by Step mode"},i++);
		terms.put(new String[]{"Brk-Modus","Brk mode"},i++);
		/*terms.put(new String[]{},i++);
		terms.put(new String[]{},i++);
		terms.put(new String[]{},i++);
		terms.put(new String[]{},i++);
		terms.put(new String[]{},i++);
		terms.put(new String[]{},i++);*/
	}
}
