/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.serviceImpls;

import edu.hdu.lab.mapper.SuggestionMapper;
import edu.hdu.lab.model.Suggestion;
import edu.hdu.lab.services.SuggestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author justin
 */
@Service("SuggestionService")
public class SuggestionServiceImpl 
    implements SuggestionService{

    @Autowired
    private SuggestionMapper suggestionMapper;
    
    public List<Suggestion> getSuggestions(Suggestion suggestion) {
        List<Suggestion> list = suggestionMapper.getSuggestions(suggestion);
        
        return list;
    }

    public int addSuggestion(Suggestion suggestion) {
        return
                suggestionMapper.insert(suggestion);
    }

    public int updateSuggestion(Suggestion suggestion) {
        return
                suggestionMapper.updateByPrimaryKeySelective(suggestion);
    }

    public Suggestion getSuggestionById(Integer id) {
        return
                suggestionMapper.selectByPrimaryKey(id);
    }
    
}
