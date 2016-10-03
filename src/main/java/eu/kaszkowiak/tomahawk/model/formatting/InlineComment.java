/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.model.formatting;

/**
 *
 * @author kan
 */
public class InlineComment extends Comment {

    public InlineComment(String text) {
        super(text);
    }
    
    @Override
    public String toString() {    
        return text;
    }
}
