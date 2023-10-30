package com.main.backend.features.tree.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "nodes")
public class NodeEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String label;
    private Integer stepValue;

    @OneToMany(mappedBy = "parentNode", cascade = CascadeType.REMOVE)
    private List<NodeEntity> childNodes;

    @ManyToOne
    private NodeEntity parentNode;

    public int getPathValue() {
        int pathValue = (hasStepValue() ? stepValue : 0);

        NodeEntity currentParent = parentNode;
        while (currentParent != null && currentParent.hasStepValue()) {
            pathValue += currentParent.getStepValue();
            currentParent = currentParent.getParentNode();
        }

        return pathValue;
    }

    public boolean hasStepValue() {
        return stepValue != null;
    }
}
