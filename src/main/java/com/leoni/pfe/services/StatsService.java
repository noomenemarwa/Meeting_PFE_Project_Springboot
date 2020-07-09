package com.leoni.pfe.services;

import com.leoni.pfe.accessingdatajpa.Departement;
import com.leoni.pfe.accessingdatajpa.MembreDeReunion;
import com.leoni.pfe.accessingdatajpa.Personne;
import com.leoni.pfe.accessingdatajpa.dto.Department;
import com.leoni.pfe.accessingdatajpa.dto.StatsParDepartment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    private final ReunionService reunionService;
    private final MembreDeReunionService membreDeReunionService;
    private final PersonneService personneService;
    private final DepartmentService departmentService;

    public StatsService(ReunionService reunionService,
                        MembreDeReunionService membreDeReunionService, PersonneService personneService, DepartmentService departmentService) {
        this.reunionService = reunionService;
        this.membreDeReunionService = membreDeReunionService;
        this.personneService = personneService;
        this.departmentService = departmentService;
    }

    // ***************************

    // "2020-11-15 10:00:00", "2020-11-17 12:00:00"
    public StatsParDepartment statsParDepartements(String startDate, String endDate) {
        StatsParDepartment statsParDepartment = new StatsParDepartment();
        statsParDepartment.setDateDeb(startDate);
        statsParDepartment.setDateFin(endDate);

        List<Department> depsListStats = new ArrayList<>(0);

        // ***** REUNION
        List<Long> reunionIds = reunionService.findReunionBetweenStartAndEndDate(startDate, endDate);

        // ***** MEMBRE DE REUNION
        List<MembreDeReunion> list = membreDeReunionService.findAllForReunionList(reunionIds);
        if (list != null && !list.isEmpty()) {
            for (MembreDeReunion mbr : list) {
                System.out.println("mbr: " + mbr.toString());
            }

            List<Long> personneIds = new ArrayList<>(0);
            for (MembreDeReunion mbrr : list) {
                long idMembre = mbrr.getIdMembre();
                if (!personneIds.contains(idMembre)) {
                    personneIds.add(idMembre);
                }
            }

            // thama mochkla hna
            /*Set<Long> dumbPersonne = new HashSet<>(0);
            List<Long> personneIds = list.stream()
                    .filter(membreDeReunion -> !dumbPersonne.add(membreDeReunion.getIdMembre()))
                    .map(membreDeReunion -> {
                        // personneIds.add(membreDeReunion.getIdMembre());
                        return membreDeReunion.getIdMembre();
                    }).collect(Collectors.toList());*/
            System.out.println("printing unique personne ids:");
            personneIds.forEach(System.out::println);

            if (personneIds != null && !personneIds.isEmpty()) {
                List<Long> idDeps = personneService.findIdDepByPersonneIds(personneIds);

                if (idDeps != null && !idDeps.isEmpty()) {
                    System.out.println("printing id deps:");
                    idDeps.forEach(System.out::println);

                    List<Personne> listPersonne = personneService.findByPersonneIds(personneIds);

                    // ***** DEPARTMENTS
                    List<Departement> depsList = departmentService.findAllDepByIds(idDeps);

                    if (depsList != null && !depsList.isEmpty()) {
                        // todo stats
                        for (Departement dep : depsList) {
                            Department department = new Department();
                            department.setIdDep(dep.getId());
                            department.setNomDep(dep.getNom());

                            int total = 0, present = 0, abscent = 0;

                            for (Personne personne : listPersonne) {
                                if (personne.getIdDep() == dep.getId()) {
                                    for (MembreDeReunion mbr : list) {
                                        if (personne.getId() == mbr.getIdMembre()) {
                                            total++;
                                            if (mbr.getIsPresent() == 1) {
                                                present++;
                                            } else {
                                                abscent++;
                                            }
                                        }
                                    }
                                }
                            }

                            // calcul de pourcentage
                            // formule de pourcentage ? NOMBRE D'abs/total*100 totale mta3 les membre fi reunion fi département wa7ed
                            // thinking ... :p
                            // (absence * 100) / total normlament haka ?
                            System.out.println(String.format("total: %d, present: %d, abscent: %d", total, present, abscent));
                            if (total > 0) {
                                int pourcentage = (abscent * 100) / total;
                                System.out.println("pourcentage dep: " + department.getIdDep() + " => " + pourcentage);
                                department.setPourcentageAbscence(pourcentage);
                                department.setTotal(total);
                                department.setAbscent(abscent);
                            }

                            depsListStats.add(department);
                        }

                    }

                }
            }

        } else {
            System.out.println("no meeting attendee");
        }

        statsParDepartment.setDepartments(depsListStats);

        return statsParDepartment;
    }
}
