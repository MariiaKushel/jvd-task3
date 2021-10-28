package by.javacourse.task3.builder;

import java.util.HashSet;
import java.util.Set;

import by.javacourse.task3.entity.MedProduct;

public abstract class AbstractMedProductBuilder {
	
	protected Set<MedProduct> medCatalog;
	
	public AbstractMedProductBuilder () {
		medCatalog = new HashSet<MedProduct>();
	}

	public AbstractMedProductBuilder (Set<MedProduct> medCatalog) {
		this.medCatalog = medCatalog;
	}

	public Set<MedProduct> getMedCatalog() {
		return medCatalog;
	}

	public void setMedCatalog(Set<MedProduct> medCatalog) {
		this.medCatalog = medCatalog;
	}
	
	public abstract void buildMedCatalog (String xmlPath);
}
