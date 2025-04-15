CREATE TABLE calendrier(
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            titre VARCHAR(50),
                            dateDebut VARCHAR(100),
                            dateFin VARCHAR(100),
                            emplacement VARCHAR(100),
                            type VARCHAR(100)

);


INSERT INTO calendrier(titre, dateDebut, dateFin,emplacement,type)
VALUES('JUnit','2025/04/12','2025/04/30','CISCO',"ceremonie"),
    ('JIRA','2025/04/12','2025/04/30','CISCO',"ceremonie"),
    ('DOCKEUR','2025/04/12','2025/04/30','CISCO',"ceremonie");

