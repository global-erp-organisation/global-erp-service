package com.camlait.global.erp.delegate.produit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.camlait.global.erp.domain.exception.DataStorageException;
import com.camlait.global.erp.domain.product.Product;
import com.camlait.global.erp.domain.product.ProductCategory;

public interface ProductManager {

    /**
     * Add a product in to the data storage.
     * 
     * @param product Product to store
     * @return The stored product.
     * @throws DataStorageException
     */
    Product addProduct(Product product) throws DataStorageException;

    /**
     * Update the given product information in the data store.
     * 
     * @param product Given product.
     * @return The updated product.
     * @throws DataStorageException
     */
    Product updateProduct(Product product) throws DataStorageException;

    /**
     * Retrieves a product from the data storage based on the given identifier.
     * 
     * @param productId Product Identifier.
     * @return The product informations that belongs to the given identifier.
     * @throws DataStorageException
     */
    Product retrieveProduct(String productId) throws DataStorageException;

    /**
     * Permanently remove a product from the data storage.
     * 
     * @param productId product Identifier.
     * @return True if the operation is performed without error or false
     *         otherwise.
     * @throws DataStorageException
     */
    Boolean removeProduct(String productId) throws DataStorageException;

    /**
     * Retrieves products from the data storage based on the given key word.
     * 
     * @param keyWord provided key word.
     * @param p Pageable object that indicated how many records need to be extracted per page.
     * @return All products that belong to the given key word.
     * @throws DataStorageException
     */
    Page<Product> retriveProducts(String keyWord, Pageable p) throws DataStorageException;

    /**
     * Add a product category in to the data storage.
     * 
     * @param productCategory Product category to store
     * @return The stored product category.
     * @throws DataStorageException
     */
    ProductCategory addProductCategory(ProductCategory productCategory) throws DataStorageException;

    /**
     * Update the given product category informations in the data store.
     * 
     * @param productCategory Given product.
     * @return The updated product category.
     * @throws DataStorageException
     */
    ProductCategory updateProductCategory(ProductCategory productCategory) throws DataStorageException;

    /**
     * Retrieves a product category from the data storage based on the given
     * identifier.
     * 
     * @param productCategoryId Product category Identifier.
     * @return The product category informations that belongs to the given
     *         identifier.
     * @throws DataStorageException
     */
    ProductCategory retrieveProductCategory(String productCategoryId) throws DataStorageException;

    /**
     * Permanently remove a product category from the data storage.
     * 
     * @param productCategoryId product category Identifier.
     * @return True if the operation is performed without error or false
     *         otherwise.
     * @throws DataStorageException
     */
    Boolean removeProductCategory(String productCategoryId) throws DataStorageException;

    /**
     * Retrieves product categories from the data storage based on the given key
     * word.
     * 
     * @param keyWord provided key word.
     * @param p Pageable object that indicated how many records need to be extracted per page.
     * @return All product categories that belong to the given key word.
     * @throws DataStorageException
     */
    Page<ProductCategory> retriveProductCategories(String keyWord, Pageable p) throws DataStorageException;
}
