import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class IOPut 
{
	File bs =new File("commands.bs");
	Befehlsdatenbank befehl_DB;
	public IOPut(Befehlsdatenbank befehlDB)
	{
		this.befehl_DB=befehlDB;
		this.readfile();
	}
	public void readfile()
	{
		Scanner sc =null;
		String line,sp[],sp2[]=new String[2];
		byte sp3[]=new byte[2],sp4[]=new byte[2];
		try 
		{
			sc =new Scanner(this.bs);
		} catch (FileNotFoundException e) 
		{
			System.out.println("commands.bs nicht gefunden");
		}
		if(sc!=null)
			while(sc.hasNextLine())
			{
				line=sc.nextLine();
				sp=line.split("\\t\\t");
				sp2=sp[3].split("/");
				sp3[0]=Byte.parseByte(sp2[0]);
				sp3[1]=Byte.parseByte(sp2[1]);
				sp2=sp[1].split(",");
				sp4[0]=Byte.parseByte(sp2[0]);
				sp4[1]=Byte.parseByte(sp2[1]);
				sp2=new String[5];
				for(byte i=0 ; i<5 ; i++)
					sp2[i]=""+sp[5].charAt(i);
				this.befehl_DB.addEntry(Byte.parseByte(sp[4],2), Byte.parseByte(sp[2]) ,sp[0],sp3,sp4,sp2);
			}
		else
			GUI.errormessage("Doesn't found Commands File",true);
	}

}
