/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.services;

import edu.hdu.lab.model.Suggestion;
import java.util.List;

/**
 *
 * @author justin
 */
public interface SuggestionService {
    
    public List<Suggestion> getSuggestions(Suggestion suggestion);
    
    public int addSuggestion(Suggestion suggestion);
    
    public int updateSuggestion(Suggestion suggestion);
    
    public Suggestion getSuggestionById(Integer id);
}
