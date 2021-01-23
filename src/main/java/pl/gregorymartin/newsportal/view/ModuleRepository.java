package pl.gregorymartin.newsportal.view;


import org.springframework.data.jpa.repository.JpaRepository;

interface ModuleRepository extends JpaRepository<Module, Integer> {
}
