package com.leoni.pfe.services;


import com.leoni.pfe.accessingdatajpa.Personne;
import com.leoni.pfe.accessingdatajpa.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class PersonneService {

    private final PersonneRepository personneRepository;

    @Autowired
    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }


    public List<Personne> findAllPersonne() {
        System.out.println("generated random password: " + generatePassword());
        return personneRepository.findAll();
    }

    public Personne login(String email, String password) {
        List<Personne> list = personneRepository.findByEmailAndPassword(email, password);
        return list.get(0);
    }

    public Personne savePersonne(int matricule,
                                 long idDep,
                                 String prenom,
                                 String nom,
                                 String fonction,
                                 String type,
                                 String email,
                                 String cin,
                                 String password) {

        System.out.println("cin received in service: " + cin);

        String passwd = password;

        if (!type.equals("admin")) {
            passwd = generatePassword();
        }

        Personne personne = new Personne();
        personne.setMatricule(matricule);
        personne.setIdDep(idDep);
        personne.setEmail(email);
        personne.setFonction(fonction);
        personne.setNom(nom);
        personne.setPrenom(prenom);
        personne.setPassword(passwd);
        personne.setCin(cin);
        personne.setType(type);

        return personneRepository.save(personne);
        /*System.out.println("Saved personne: " + personne.toString());

        return personne;*/
    }

    @Transactional
    public boolean updatePersonne(long id,
                                  long idDep,
                                  int matricule,
                                  String prenom,
                                  String nom,
                                  String fonction,
                                  String type,
                                  String email,
                                  String cin,
                                  String password) {
        int updateCount = personneRepository.updatePersonne(fonction, email, password, prenom, nom, type, matricule, cin, idDep, id);
        return updateCount > 0; //true:la modification est effectuée
    }

    @Transactional
    public void deletePersonne(long id) {
        personneRepository.deleteById(id);
    }

    // ***************************

    private String generatePassword() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        String password = sdf.format(new Date()) + "abc";
        return password;
    }
}
