    package com.example.dbd.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import com.example.dbd.models.DetailEvaluation;

    import com.example.dbd.models.Evaluation;
    import java.util.List;

    public interface DetailEvaluationRepository extends JpaRepository<DetailEvaluation, Integer> {
        List<DetailEvaluation> findByEvaluation(Evaluation evaluation);
    }
