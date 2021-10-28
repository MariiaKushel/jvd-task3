package by.javacourse.task3.entity;

import java.time.YearMonth;
import java.util.List;

public abstract class MedProduct {

	public static final boolean DEFAULT_OUT_OF_PRODACTION = false;

	private String medProductId;
	private boolean outOfProdaction;
	private String name;
	private String pharm;
	private GroupATC group;
	private List<String> analogs;
	private Version version = new Version();

	public MedProduct() {

	}

	public MedProduct(String medProductID, boolean outOfProdaction, String name, String pharm, GroupATC group,
			List<String> analogs, Version version) {
		this.medProductId = medProductID;
		this.outOfProdaction = outOfProdaction;
		this.name = name;
		this.pharm = pharm;
		this.group = group;
		this.analogs = analogs;
		this.version = version;
	}

	public String getMedProdactId() {
		return medProductId;
	}

	public void setMedProdactId(String medicinsId) {
		this.medProductId = medicinsId;
	}

	public boolean isOutOfProdaction() {
		return outOfProdaction;
	}

	public void setOutOfProdaction(boolean outOfProdaction) {
		this.outOfProdaction = outOfProdaction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPharm() {
		return pharm;
	}

	public void setPharm(String pharm) {
		this.pharm = pharm;
	}

	public GroupATC getGroup() {
		return group;
	}

	public void setGroup(GroupATC group) {
		this.group = group;
	}

	public List<String> getAnalogs() {
		return analogs;
	}

	public void setAnalogs(List<String> analogs) {
		this.analogs = analogs;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((analogs == null) ? 0 : analogs.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((medProductId == null) ? 0 : medProductId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (outOfProdaction ? 1231 : 1237);
		result = prime * result + ((pharm == null) ? 0 : pharm.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MedProduct other = (MedProduct) obj;
		if (analogs == null) {
			if (other.analogs != null) {
				return false;
			}
		} else if (!analogs.equals(other.analogs)) {
			return false;
		}
		if (group != other.group) {
			return false;
		}
		if (medProductId == null) {
			if (other.medProductId != null) {
				return false;
			}
		} else if (!medProductId.equals(other.medProductId)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (outOfProdaction != other.outOfProdaction) {
			return false;
		}
		if (pharm == null) {
			if (other.pharm != null) {
				return false;
			}
		} else if (!pharm.equals(other.pharm)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("MedProduct [medProductId=")
				.append(medProductId)
				.append(", outOfProdaction=")
				.append(outOfProdaction)
				.append(", name=")
				.append(name)
				.append(", pharm=")
				.append(pharm)
				.append(", group=")
				.append(group)
				.append(", analogs=")
				.append(analogs)
				.append(", version=")
				.append(version)
				.append("]").toString();
	}

	public class Version {

		private Country country;
		private String certificate;
		private YearMonth dataFrom;
		private YearMonth dataTo;
		private Pack pack;
		private String dosage;

		public Version() {

		}

		public Version(Country country, String certificate, YearMonth dataFrom, YearMonth dataTo, Pack pack,
				String dosage) {
			this.country = country;
			this.certificate = certificate;
			this.dataFrom = dataFrom;
			this.dataTo = dataTo;
			this.pack = pack;
			this.dosage = dosage;
		}

		public Country getCountry() {
			return country;
		}

		public void setCountry(Country country) {
			this.country = country;
		}

		public String getCertificate() {
			return certificate;
		}

		public void setCertificate(String certificate) {
			this.certificate = certificate;
		}

		public YearMonth getDataFrom() {
			return dataFrom;
		}

		public void setDataFrom(YearMonth dataFrom) {
			this.dataFrom = dataFrom;
		}

		public YearMonth getDataTo() {
			return dataTo;
		}

		public void setDataTo(YearMonth dataTo) {
			this.dataTo = dataTo;
		}

		public Pack getPack() {
			return pack;
		}

		public void setPack(Pack pack) {
			this.pack = pack;
		}

		public String getDosage() {
			return dosage;
		}

		public void setDosage(String dosage) {
			this.dosage = dosage;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((certificate == null) ? 0 : certificate.hashCode());
			result = prime * result + ((country == null) ? 0 : country.hashCode());
			result = prime * result + ((dataFrom == null) ? 0 : dataFrom.hashCode());
			result = prime * result + ((dataTo == null) ? 0 : dataTo.hashCode());
			result = prime * result + ((dosage == null) ? 0 : dosage.hashCode());
			result = prime * result + ((pack == null) ? 0 : pack.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Version other = (Version) obj;
			
			if (certificate == null) {
				if (other.certificate != null) {
					return false;
				}
			} else if (!certificate.equals(other.certificate)) {
				return false;
			}
			if (country != other.country) {
				return false;
			}
			if (dataFrom == null) {
				if (other.dataFrom != null) {
					return false;
				}
			} else if (!dataFrom.equals(other.dataFrom)) {
				return false;
			}
			if (dataTo == null) {
				if (other.dataTo != null) {
					return false;
				}
			} else if (!dataTo.equals(other.dataTo)) {
				return false;
			}
			if (dosage == null) {
				if (other.dosage != null) {
					return false;
				}
			} else if (!dosage.equals(other.dosage)) {
				return false;
			}
			if (pack != other.pack) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return new StringBuilder()
					.append("Version [country=")
					.append(country)
					.append(", certificate=")
					.append(certificate)
					.append(", dataFrom=")
					.append(dataFrom)
					.append(", dataTo=")
					.append(dataTo)
					.append(", pack=")
					.append(pack)
					.append(", dosage=")
					.append(dosage)
					.append("]")
					.toString();
		}
	}
}
