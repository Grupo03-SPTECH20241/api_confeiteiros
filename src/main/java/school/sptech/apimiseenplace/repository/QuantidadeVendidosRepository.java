package school.sptech.apimiseenplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apimiseenplace.view.VwQuantidadeVendidosMes;

public interface QuantidadeVendidosRepository extends JpaRepository<VwQuantidadeVendidosMes, Integer> {
}
