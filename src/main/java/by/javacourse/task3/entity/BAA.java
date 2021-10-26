package by.javacourse.task3.entity;

import java.util.List;

public class BAA extends Medicins {

	private List<String> composition;

	public BAA() {
		super();
	}

	public BAA(String medicationId, boolean outOfProdaction, String name, String pharm, GroupATC group,
			List<String> analogs, Version version, List<String> composition) {
		super(medicationId, outOfProdaction, name, pharm, group, analogs, version);
		this.composition = composition;
	}

	public List<String> getComposition() {
		return composition;
	}

	public void setComposition(List<String> composition) {
		this.composition = composition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((composition == null) ? 0 : composition.hashCode());
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
		BAA other = (BAA) obj;
		if (composition == null) {
			if (other.composition != null) {
				return false;
			}
		} else if (!composition.equals(other.composition)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("BAA [")
				.append(super.toString())
				.append(" composition=")
				.append(composition)
				.append("]")
				.toString();
	}

}
