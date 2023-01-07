package ru.denisss.footballplayerscatalog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.denisss.footballplayerscatalog.entity.Footballer;


public interface FootballerRepository  extends JpaRepository<Footballer,Long> {

    Page<Footballer> findAll(Pageable pageable);

}
