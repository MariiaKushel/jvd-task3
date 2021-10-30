package by.javacourse.task3.builder;

import java.util.HashSet;
import java.util.Set;

import by.javacourse.task3.entity.MedProduct;
import by.javacourse.task3.exeption.MedProductException;

public abstract class MedProductBuilder {
	
	protected Set<MedProduct> medCatalog;
	
	public MedProductBuilder () {
		medCatalog = new HashSet<MedProduct>();
	}

	public MedProductBuilder (Set<MedProduct> medCatalog) {
		this.medCatalog = medCatalog;
	}

	public Set<MedProduct> getMedCatalog() {
		return medCatalog;
	}

	public void setMedCatalog(Set<MedProduct> medCatalog) {
		this.medCatalog = medCatalog;
	}
	
	public abstract void buildMedCatalog (String xmlPath) throws MedProductException;
}
