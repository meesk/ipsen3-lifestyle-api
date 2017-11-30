/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.lifestyle.api.model.Knowledge;

/**
 *
 * @author Laurens Jan
 */
@Singleton
public class KnowledgeDAO {

    private final List<Knowledge> knows;

    @Inject
    public KnowledgeDAO() {
        knows = new ArrayList<>();
    }

    public void addBulk(Knowledge[] knowledge) {
        for (Knowledge x : knowledge) {
            this.knows.add(x);
        }
    }

    public void add(Knowledge knowledge) {
        this.knows.add(knowledge);
    }

    public List<Knowledge> getAll() {
        return this.knows;
    }
}
