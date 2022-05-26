package com.isita.ccapper.app.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isita.ccapper.app.entity.Employee;



@Repository
public class Employeedao {
	@Autowired
	private EntityManager em;

	/**
	 * Method to fetch all employees from the db.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Iterable<Employee> getAllEmployees() {
		return em.createNamedStoredProcedureQuery("procedure-one").getResultList();
	}

	/**
	 * Method to fetch employees on the basis of department.
	 * @param input
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Iterable<Employee> getEmployeesByDepartment(String input) {
		return em.createNamedStoredProcedureQuery("procedure-two").setParameter("emp_department", input).getResultList();
	}

	/**
	 * Method to fetch the employees count on the basis of designation.
	 * @param edesignation
	 * @return
	 */
	public Integer getEmployeesCountByDesignation(String input) {
		return (Integer) em.createNamedStoredProcedureQuery("procedure-third").setParameter("emp_designation", input).getOutputParameterValue("designation_count");
	}
}