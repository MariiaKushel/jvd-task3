package by.javacourse.task3.entity;

import java.util.List;

public class Medicine extends MedProduct {

	private String codeCAS;
	private String activeSubstance;
	private boolean needRecipe;

	public Medicine() {
		super();
	}

	public Medicine(String medProductId, boolean outOfProdaction, String name, String pharm, GroupATC group,
			List<String> analogs, Version version, String codeCAS, String activeSubstance, boolean needRecipe) {
		super(medProductId, outOfProdaction, name, pharm, group, analogs, version);
		this.codeCAS = codeCAS;
		this.activeSubstance = activeSubstance;
		this.needRecipe = needRecipe;
	}

	public String getCodeCAS() {
		return codeCAS;
	}

	public void setCodeCAS(String codeCAS) {
		this.codeCAS = codeCAS;
	}

	public String getActiveSubstance() {
		return activeSubstance;
	}

	public void setActiveSubstance(String activeSubstance) {
		this.activeSubstance = activeSubstance;
	}

	public boolean isNeedRecipe() {
		return needRecipe;
	}

	public void setNeedRecipe(boolean needRecipe) {
		this.needRecipe = needRecipe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((activeSubstance == null) ? 0 : activeSubstance.hashCode());
		result = prime * result + ((codeCAS == null) ? 0 : codeCAS.hashCode());
		result = prime * result + (needRecipe ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Medicine other = (Medicine) obj;
		if (activeSubstance == null) {
			if (other.activeSubstance != null) {
				return false;
			}
		} else if (!activeSubstance.equals(other.activeSubstance)) {
			return false;
		}
		if (codeCAS == null) {
			if (other.codeCAS != null) {
				return false;
			}
		} else if (!codeCAS.equals(other.codeCAS)) {
			return false;
		}
		if (needRecipe != other.needRecipe) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Medicine [")
				.append(super.toString())
				.append(" codeCAS=")
				.append(codeCAS)
				.append(", activeSubstance=")
				.append(activeSubstance)
				.append(", needRecipe=")
				.append(needRecipe)
				.append("]")
				.toString();
	}
}
