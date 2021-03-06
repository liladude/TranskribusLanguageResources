package eu.transkribus.languageresources.interfaces;

import eu.transkribus.interfaces.languageresources.ITextExtractor;
import java.util.List;

import eu.transkribus.interfaces.IDictionary;

/**
 *
 * @author jnphilipp
 */
public interface IPagewiseTextExtractor extends ITextExtractor
{

    List<String> extractTextFromDocumentPagewise(String path);

    String extractTextFromPage(String path, int page);

    IDictionary extractAbbreviationsFromPage(String path, int page);
}
