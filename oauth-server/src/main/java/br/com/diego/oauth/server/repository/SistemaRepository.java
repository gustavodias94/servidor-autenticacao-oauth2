/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.repository;

import br.com.diego.oauth.server.entidades.Sistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego NOTE
 */
@Repository
public class SistemaRepository extends GenericRepository<Sistema> {
    
    public Boolean isClientIdValido(String clientSecret) {
        Query query = entityManager.createQuery("SELECT s FROM Sistema s WHERE s.clientSecret =:clientSecret");
        query.setParameter("clientSecret", clientSecret);
        
        List<Sistema> sistemas = query.getResultList();
        
        return !sistemas.isEmpty();
    }
    
    public Sistema buscarPorClietSecret(String clientSecret) {
        Query query = entityManager.createQuery("SELECT s FROM Sistema s WHERE s.clientSecret =:clientSecret");
        query.setParameter("clientSecret", clientSecret);
        
        try {
             return (Sistema) query.getSingleResult();
        } catch (NonUniqueResultException e) {
            Logger.getLogger(SistemaRepository.class.getName()).log(Level.INFO, null, e);
            return null;
        }
    }
}