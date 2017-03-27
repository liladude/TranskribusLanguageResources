/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.transkribus.languageresources.util;

import eu.transkribus.interfaces.IEntry;
import eu.transkribus.languageresources.exceptions.ARPAParseException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author max
 */
public class SimpleDictFileHandler
{

    public static Map<String, Integer> read(String file) throws ARPAParseException, IOException
    {
        return read(new File(file));
    }

    public static Map<String, Integer> read(File file) throws ARPAParseException, FileNotFoundException, IOException
    {
        Map<String, Integer> dict = new HashMap<>();

        Reader reader = new BufferedReader(new FileReader(file));
        while (true)
        {
            String line = ((BufferedReader) reader).readLine();
            if (line == null)
            {
                break;
            }

            String[] parts = line.split(",");
            dict.put(parts[0], new Integer(parts[1]));
        }
        reader.close();

        return dict;
    }

    public static void write(File file, Collection<IEntry> entries) throws IOException
    {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ROOT);
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setGroupingUsed(false);

        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        for (IEntry entry : entries)
        {
            writer.println(String.format("%s,%d", entry.getKey(), entry.getFrequency()));
        }
        writer.close();
    }
}
