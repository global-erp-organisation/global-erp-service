package com.camlait.global.erp.delegate.document;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.camlait.global.erp.domain.document.Document;
import com.camlait.global.erp.domain.document.commerciaux.Taxe;
import com.camlait.global.erp.domain.exception.DataStorageException;

public interface DocumentManager {

	/**
	 * Add the provided document to the data storage
	 * 
	 * @param document
	 *            Document to store.
	 * @return The stored document.
	 * @throws DataStorageException
	 */
	Document addDocument(Document document) throws DataStorageException;

	/**
	 * Update the provided document to the data storage.
	 * 
	 * @param document
	 *            Document thet need to be updated.
	 * @return The updated document.
	 * @throws DataStorageException
	 */
	Document updateDocument(Document document) throws DataStorageException;

	/**
	 * Retrieves the document from the data storage based on the given
	 * doumentId.
	 * 
	 * @param documentId
	 *            Provided documentId.
	 * @return The document that belongs to the given documentId.
	 * @throws DataStorageException
	 */
	Document retrieveDocument(String documentId) throws DataStorageException;

	/**
	 * Retrieves a generic type of document
	 * 
	 * @param clazz
	 *            Concrete document class to retrieve.
	 * @param documentId
	 *            Document identifier.
	 * @return Return the document that belongs to the given identifier.
	 * @throws DataStorageException
	 */
	<T> T retrieveDocument(Class<T> clazz, String documentId) throws DataStorageException;

	/**
	 * Permanently remove a document from the data storage.
	 * 
	 * @param documentId
	 *            Document to remove identifier.
	 * @return true if the operation is performed without error or false
	 *         otherwise.
	 * @throws DataStorageException
	 */
	Boolean removeDocument(String documentId) throws DataStorageException;

	/**
	 * Retrieves some documents from the data storage based on the given key
	 * word.
	 * 
	 * @param keyWord
	 *            Key word to use.
	 * @param p
	 *            Pageable object that indicated how many records need to be
	 *            extracted per page.
	 * @return All the documents that belong to the provided key word.
	 * @throws DataStorageException
	 */
	Page<Document> retrieveDocuments(String keyWord, Pageable p) throws DataStorageException;

	/**
	 * Retrieves documents base on the given period.
	 * 
	 * @param start
	 *            Beginning date
	 * @param end
	 *            Ending date.
	 * @param p
	 *            Pageable object that indicated how many records need to be
	 *            extracted per page.
	 * @return All The documents within the given period.
	 * @throws DataStorageException
	 */
	Page<Document> retrieveDocuments(Date start, Date end, Pageable p) throws DataStorageException;

	/**
	 * Computes the value without taxes for the document.
	 * 
	 * @param documentId
	 *            Document identifier.
	 * @return The value without taxes for the given document.
	 * @throws DataStorageException
	 */
	Double documentValueWithoutTaxes(String documentId) throws DataStorageException;

	/**
	 * Computes the taxes value for a document.
	 * 
	 * @param documentId
	 *            Document identifier.
	 * @return The total taxes value that belong to the given document
	 *         identifier.
	 * @throws DataStorageException
	 */
	Double documentTaxesValue(String documentId) throws DataStorageException;

	/**
	 * Computes the taxes value for a document.
	 * 
	 * @param taxId
	 *            Tax identifier.
	 * @param documentId
	 *            Document identifier.
	 * @return The total tax value for the given tax and the given document.
	 * @throws DataStorageException
	 */
	Double documentTaxesValue(String taxId, String documentId) throws DataStorageException;

	/**
	 * Computes the document value including taxes value.
	 * 
	 * @param documentId
	 *            Document identifier.
	 * @return The document value including taxes value.
	 * @throws DataStorageException
	 */
	Double documentValueWithTaxes(String documentId) throws DataStorageException;

	/**
	 * Computes the document margin value
	 * 
	 * @param documentId
	 *            Document identifier.
	 * @return The document margin value
	 *         <p>
	 *         The returned value already include taxes value.
	 * @throws DataStorageException
	 */
	Double documentMarginValue(String documentId) throws DataStorageException;

	/**
	 * Add a tax in the data storage.
	 * 
	 * @param tax
	 *            Tax to store
	 * @return The stored tax.
	 * @throws DataStorageException
	 */
	Taxe addTax(Taxe tax) throws DataStorageException;

	/**
	 * Update a tax
	 * 
	 * @param tax
	 *            Tax to update
	 * @return The updated tax
	 * @throws DataStorageException
	 */
	Taxe updateTax(Taxe tax) throws DataStorageException;

	/**
	 * Retrieves a tax type from the data storage.
	 * 
	 * @param taxId
	 *            Tax Identifier
	 * @return The tax that belongs to the given identifier.
	 * @throws DataStorageException
	 */
	Taxe retrieveTax(String taxId) throws DataStorageException;

	/**
	 * Permanently removes a tax from the data storage.
	 * 
	 * @param taxId
	 *            Tax identifier.
	 * @return true if the operation is performed without error or false
	 *         otherwise.
	 * @throws DataStorageException
	 */
	Boolean removeTax(String taxId) throws DataStorageException;

	/**
	 * Retrieves taxes type from the data storage based on the given key word.
	 * 
	 * @param keyWord
	 *            Key word
	 * @param p
	 *            Pageable object that indicated how many records need to be
	 *            extracted per page.
	 * @return
	 * @throws DataStorageException
	 */
	Page<Taxe> retrieveTaxes(String keyWord, Pageable p) throws DataStorageException;

}