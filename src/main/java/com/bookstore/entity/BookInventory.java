package com.bookstore.entity;


import com.bookstore.enums.InventoryStatus;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@Entity
@Table(name = "book_inventory")
public class BookInventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @OneToOne(fetch = FetchType.LAZY, targetEntity = BookCatalog.class)
  @JoinColumn(name = "catalog_id", referencedColumnName = "id")
  private BookCatalog bookCatalog;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private InventoryStatus inventoryStatus;

  @Column(name = "count")
  private Integer count;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;


}
