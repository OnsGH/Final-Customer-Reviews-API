package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired private ProductRepository productRepository;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void testFindById(){
        // create Product
        Product product = new Product();
        // set fields

        product.setProductName("product1");
        product.setProductDescription("product1Desc1");

        entityManager.persist(product);

        Product product2 = new Product();
        // set fields

        product2.setProductName("product2");
        product2.setProductDescription("product1Desc2");
        entityManager.persist(product2);

        Optional<Product> actual = productRepository.findById(2);
        assertThat(actual).isNotNull();
        assertEquals(product2.getProductId(), actual.get().getProductId());
        System.out.print("**  Actual  "+ actual.get().getProductId() +"  ** Settetd "+product2.getProductId()+"  ");
    }
}
