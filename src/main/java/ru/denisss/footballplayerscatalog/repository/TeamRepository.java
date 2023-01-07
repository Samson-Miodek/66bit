package ru.denisss.footballplayerscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.denisss.footballplayerscatalog.entity.Team;

public interface TeamRepository extends JpaRepository<Team,Long> {

    Team findByName(String name);

}
