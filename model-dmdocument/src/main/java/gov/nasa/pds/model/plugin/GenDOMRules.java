// Copyright 2019, California Institute of Technology ("Caltech").
// U.S. Government sponsorship acknowledged.
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// * Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
// * Redistributions must reproduce the above copyright notice, this list of
// conditions and the following disclaimer in the documentation and/or other
// materials provided with the distribution.
// * Neither the name of Caltech nor its operating division, the Jet Propulsion
// Laboratory, nor the names of its contributors may be used to endorse or
// promote products derived from this software without specific prior written
// permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package gov.nasa.pds.model.plugin; 
import java.io.*;
import java.util.*;

class GenDOMRules extends Object {	
	ArrayList <DeprecatedDefn> lUnitIdDeprecatedArr = new ArrayList <DeprecatedDefn> (); 
	
	public GenDOMRules () {
		return;
	}
	
//	generate schematron rules
	public void genSchematronRules () throws java.io.IOException {
//		System.out.println("\n>debug - Gen DOM Rules - Custom Rule Counts - Before generation");
//		System.out.println("debug - Rule count for Arr: " + DOMInfoModel.masterDOMRuleArr.size());
//		System.out.println("debug - Rule count for Map: " + DOMInfoModel.masterDOMRuleMap.size());
//		System.out.println("debug - Rule count for Id Map: " + DOMInfoModel.masterDOMRuleIdMap.size());		
		
		// for each namespace, generate the schematron rules
		ArrayList <SchemaFileDefn> lSchemaFileDefnArr = new ArrayList <SchemaFileDefn> (DMDocument.masterSchemaFileSortMap.values());
		for (Iterator <SchemaFileDefn> i = lSchemaFileDefnArr.iterator(); i.hasNext();) {
			SchemaFileDefn lSchemaFileDefn = (SchemaFileDefn) i.next();
			genSchematronRule(lSchemaFileDefn, DOMInfoModel.masterDOMClassMap);
		}
//		System.out.println("\n>debug - Gen DOM Rules - Custom Rule Counts - After generation");
//		System.out.println("debug - Rule count for Arr: " + DOMInfoModel.masterDOMRuleArr.size());
//		System.out.println("debug - Rule count for Map: " + DOMInfoModel.masterDOMRuleMap.size());
//		System.out.println("debug - Rule count for Id Map: " + DOMInfoModel.masterDOMRuleIdMap.size());		
		return;
	}
		
//	write the schematron rules
	public void genSchematronRule (SchemaFileDefn lSchemaFileDefn, TreeMap <String, DOMClass> lMasterDOMClassMap) {
		// add the enumerated value schematron rules		
		addSchematronRuleEnumerated (lSchemaFileDefn, lMasterDOMClassMap);
		
		// add the boolean (true, false) schematron rules		
		addSchematronRuleBoolean (lSchemaFileDefn, lMasterDOMClassMap);
		
		// add the deprecated item schematron rules	(Insert)
		if (! DMDocument.deprecatedAddedDOM) {
			DMDocument.deprecatedAddedDOM = true;
			addSchematronRuleDeprecated (lSchemaFileDefn);
			addSchematronRuleDeprecatedUnitId (lSchemaFileDefn);
		}
		
		// add the Science Facet schematron rules		
		addSchematronRuleDisciplineFacets (lSchemaFileDefn, DOMInfoModel.sfDisciplineFacetDefnMap);
				
		// add the unit values schematron rules
		addSchematronRuleUnits (lSchemaFileDefn, lMasterDOMClassMap);
				
		// If this is an LDD run set lRule.alwaysInclude to false for all customized rules
		if (lSchemaFileDefn.isLDD) {
			for (Iterator <DOMRule> i = DOMInfoModel.masterDOMRuleArr.iterator(); i.hasNext();) {
				DOMRule lRule = (DOMRule) i.next();
				lRule.alwaysInclude = false;
			}
		}
	}
		
//	add the enumerated value schematron rules
	public void addSchematronRuleEnumerated (SchemaFileDefn lSchemaFileDefn, TreeMap <String, DOMClass> lMasterDOMClassMap) {
		// add class attributes
		ArrayList <DOMClass> lSortClassArr = new ArrayList <DOMClass> (lMasterDOMClassMap.values());
		for (Iterator <DOMClass> i = lSortClassArr.iterator(); i.hasNext();) {
			DOMClass lClass = (DOMClass) i.next();
			if ((lClass == null) || (! ((lSchemaFileDefn.nameSpaceIdNC.compareTo(lClass.nameSpaceIdNC) == 0) && lSchemaFileDefn.stewardArr.contains(lClass.steward)))) continue;
			if (lClass.isUSERClass || lClass.isUnitOfMeasure || lClass.isDataType || lClass.isVacuous) continue;
			if (lClass.allEnumAttrArr == null || lClass.allEnumAttrArr.isEmpty()) continue;

			// discipline facet and facet group schema rules are hard coded elsewhere.
			if (lClass.title.compareTo("Discipline_Facets") == 0 || lClass.title.compareTo("Group_Facet1") == 0 || lClass.title.compareTo("Group_Facet2") == 0) continue;
			
			String lDeprecatedClassIdentifier = lClass.nameSpaceIdNC + "." + lClass.title;
			addClassSchematronRuleEnumerated (lClass.nameSpaceIdNC, lClass.title, lClass.steward, lDeprecatedClassIdentifier, lClass.allEnumAttrArr);
		} 
		
		// add no-class attributes
		ArrayList <DOMAttr> lEnumAttrArr = new ArrayList <DOMAttr> ();
		ArrayList <DOMAttr> lAttrArr = new ArrayList <DOMAttr> (DOMInfoModel.userSingletonDOMClassAttrIdMap.values());
		for (Iterator <DOMAttr> i = lAttrArr.iterator(); i.hasNext();) {
			DOMAttr lAttr = (DOMAttr) i.next();
			if ((lSchemaFileDefn.nameSpaceIdNC.compareTo(lAttr.nameSpaceIdNC) != 0)) continue;
			lEnumAttrArr.add(lAttr);
		}
		if (! lEnumAttrArr.isEmpty()) addClassSchematronRuleEnumerated (DMDocument.masterNameSpaceIdNCLC, DMDocument.LDDToolSingletonClassTitle, lSchemaFileDefn.stewardArr.get(0), "", lEnumAttrArr); 
	}
	
	public void addClassSchematronRuleEnumerated (String lClassNameSpaceIdNC, String lClassTitle, String lClassSteward, String lDeprecatedClassIdentifier, ArrayList <DOMAttr> lAttrArr) {	
		for (Iterator <DOMAttr> j = lAttrArr.iterator(); j.hasNext();) {
			DOMAttr lAttr = (DOMAttr) j.next();
			String lRuleId = lClassNameSpaceIdNC + ":" + lClassTitle  + "/" + lAttr.nameSpaceIdNC + ":" + lAttr.title;;
			DOMRule lRule = DOMInfoModel.masterDOMRuleIdMap.get(lRuleId);
			if (lRule == null) {
				lRule = new DOMRule(lRuleId);
				DOMInfoModel.masterDOMRuleIdMap.put(lRule.identifier, lRule);			
				DOMInfoModel.masterDOMRuleArr.add(lRule);
				lRule.setRDFIdentifier();
				DOMInfoModel.masterDOMRuleMap.put(lRule.rdfIdentifier, lRule);
				lRule.xpath = lRuleId;
				lRule.attrTitle = "TBD_AttrTitle";		
				lRule.attrNameSpaceNC = "TBD_attrNameSpaceNC";		
				lRule.classTitle = lClassTitle;		
				lRule.classNameSpaceNC = lClassNameSpaceIdNC;
				lRule.classSteward = lClassSteward;
			}
//			String lDeprecatedAttrIdentifier = lDeprecatedClassIdentifier + "." + lAttr.title;
			if (lAttr.valArr == null || lAttr.valArr.isEmpty()) continue;
			lRule.attrTitle = lAttr.title;		
			lRule.attrNameSpaceNC = lAttr.nameSpaceIdNC;
			String lAttrId = lRule.attrNameSpaceNC + ":" + lRule.attrTitle;
			if (foundAssertStmt (lAttrId, lRule.assertArr)) continue;
			DOMAssert lAssert = new DOMAssert (lAttrId);	
			lRule.assertArr.add(lAssert);
			
			String lDel = "";
			String lDelimitedValueArr = "";
			
			// test for special attributes
			if (! (lAttr.title.compareTo("information_model_version") == 0)) {
				for (Iterator<String> k = lAttr.valArr.iterator(); k.hasNext();) {
					String lValue = (String) k.next();
					String lDelimitedValue = lDel + "'" + lValue + "'";					
					lDel = ", ";					
					lDelimitedValueArr += lDelimitedValue;	
				}

				if (lAttr.valArr.size() > 1) {
					if (! lAttr.isNilable) {
						lAssert.assertStmt = ". = (" + lDelimitedValueArr + ")";
						lAssert.assertMsg = "The attribute " + lAttrId + " must be equal to one of the following values " + lDelimitedValueArr + ".";
					} else {
						lAssert.assertStmt = "if (not(@xsi:nil eq 'true') and (not(. = (" + lDelimitedValueArr + ")))) then false() else true()";
						lAssert.assertMsg = "The attribute " + lAttrId + " must be nulled or equal to one of the following values " + lDelimitedValueArr + ".";	
					}
				} else {
					if (! lAttr.isNilable) {
						lAssert.assertStmt = ". = (" + lDelimitedValueArr + ")";
						lAssert.assertMsg = "The attribute " + lAttrId + " must be equal to the value " + lDelimitedValueArr + ".";
					} else {
						lAssert.assertStmt = "if (not(@xsi:nil eq 'true') and (not(. = (" + lDelimitedValueArr + ")))) then false() else true()";
						lAssert.assertMsg = "The attribute " + lAttrId + " must be nulled or equal to the value " + lDelimitedValueArr + ".";	
					}
				}

			// if attribute is information_model_version, only the current version is allowed			
			} else {
				String lDelimitedValue = "'" + DMDocument.masterPDSSchemaFileDefn.versionId + "'";										
				lAssert.assertStmt = ". = (" + lDelimitedValue + ")";	
				if (! lAttr.isNilable)
					lAssert.assertMsg = "The attribute " + lAttrId + " must be equal to the value " + lDelimitedValue + ".";
				else
					lAssert.assertMsg = "The attribute " + lAttrId + " must be nulled or equal to the value " + lDelimitedValue + ".";	
			}
		}
	}		
	
//	add the boolean schematron rules
	public void addSchematronRuleBoolean (SchemaFileDefn lSchemaFileDefn, TreeMap <String, DOMClass> lMasterDOMClassMap) {
		// get the set of reasonable classes
		ArrayList <DOMClass> lSelectClassArr = new ArrayList <DOMClass> ();
		ArrayList <DOMClass> lClassArr = new ArrayList <DOMClass> (lMasterDOMClassMap.values());
		for (Iterator <DOMClass> i = lClassArr.iterator(); i.hasNext();) {
			DOMClass lClass = (DOMClass) i.next();
			if ((lClass == null) || (! ((lSchemaFileDefn.nameSpaceIdNC.compareTo(lClass.nameSpaceIdNC) == 0) && lSchemaFileDefn.stewardArr.contains(lClass.steward)))) continue;
			if (lClass.isUSERClass || lClass.isUnitOfMeasure || lClass.isDataType || lClass.isVacuous) continue;
			if (lClass.allEnumAttrArr == null || lClass.allEnumAttrArr.isEmpty()) continue;
			lSelectClassArr.add(lClass);
		}
		
		// get the set of classes with Boolean data types
		ArrayList <DOMClass> lBooleanClassArr = new ArrayList <DOMClass> ();
		for (Iterator <DOMClass> i = lSelectClassArr.iterator(); i.hasNext();) {
			DOMClass lClass = (DOMClass) i.next();
			for (Iterator <DOMProp> j = lClass.allAttrAssocArr.iterator(); j.hasNext();) {
				DOMProp lDOMProp = (DOMProp) j.next();	
				if (lDOMProp.hasDOMObject != null && lDOMProp.hasDOMObject instanceof DOMAttr) {
					DOMAttr lDOMAttr = (DOMAttr) lDOMProp.hasDOMObject;				
					if (lDOMAttr.valueType.compareTo("ASCII_Boolean") == 0) {
						lBooleanClassArr.add(lClass);
						continue;
					}
				}
			}
		}
		
		// create rules for the boolean attributes
		for (Iterator <DOMClass> i = lBooleanClassArr.iterator(); i.hasNext();) {
			DOMClass lClass = (DOMClass) i.next();
			String lRuleId = lClass.nameSpaceId + lClass.title;
			DOMRule lRule = DOMInfoModel.masterDOMRuleIdMap.get(lRuleId);
			if (lRule == null) {
				lRule = new DOMRule(lRuleId);
				DOMInfoModel.masterDOMRuleIdMap.put(lRule.identifier, lRule);			
				DOMInfoModel.masterDOMRuleArr.add(lRule);
				lRule.setRDFIdentifier();
				DOMInfoModel.masterDOMRuleMap.put(lRule.rdfIdentifier, lRule);
				
				lRule.xpath = lRuleId;
				lRule.attrTitle = "TBD_AttrTitle";		
				lRule.attrNameSpaceNC = "TBD_attrNameSpaceNC";		
				lRule.classTitle = lClass.title;		
				lRule.classNameSpaceNC = lClass.nameSpaceIdNC;	
				lRule.classSteward = lClass.steward;
			}
			for (Iterator <DOMProp> j = lClass.allAttrAssocArr.iterator(); j.hasNext();) {
				DOMProp lDOMProp = (DOMProp) j.next();	
				if (lDOMProp.hasDOMObject != null && lDOMProp.hasDOMObject instanceof DOMAttr) {
					DOMAttr lDOMAttr = (DOMAttr) lDOMProp.hasDOMObject;				
					if (lDOMAttr.valueType.compareTo("ASCII_Boolean") != 0) continue;
					String assertMsgPre = " must be equal to one of the following values "; 
					lRule.attrTitle = lDOMAttr.title;		
					lRule.attrNameSpaceNC = lDOMAttr.nameSpaceIdNC;
					String lAttrId = lRule.attrNameSpaceNC + ":" + lRule.attrTitle;
					if (foundAssertStmt (lAttrId, lRule.assertArr)) continue;
					DOMAssert lAssert = new DOMAssert (lAttrId);
					lAssert.assertStmt = "if (" + lRule.attrNameSpaceNC + ":" + lRule.attrTitle + ") then " + lRule.attrNameSpaceNC + ":" + lRule.attrTitle + " = (";	
					lAssert.assertMsg =  "The attribute " + lAttrId + assertMsgPre;
					lRule.assertArr.add(lAssert);
					lAssert.assertStmt += "'true', 'false'";	
					lAssert.assertMsg += "'true', 'false'";
					lAssert.assertStmt += ") else true()";	
					lAssert.assertMsg +=  ".";
				}
			}
		} 
	}				
	
//	add the schematron rules for deprecation
	public void addSchematronRuleDeprecated (SchemaFileDefn lSchemaFileDefn) {
		String roleWarning = " role=\"warning\"";
		
		// iterate for each deprecation
		for (Iterator <DeprecatedDefn> i = DMDocument.deprecatedObjects2.iterator(); i.hasNext();) {
			DeprecatedDefn lObject = (DeprecatedDefn) i.next();
			if (lObject.isUnitId) {
				lUnitIdDeprecatedArr.add(lObject);
				continue;
			}
			String lRuleId = lObject.context + roleWarning;
			DOMRule lRule = DOMInfoModel.masterDOMRuleIdMap.get(lRuleId);
			if (lRule == null) {
				lRule = new DOMRule(lRuleId);
				DOMInfoModel.masterDOMRuleIdMap.put(lRule.identifier, lRule);			
				DOMInfoModel.masterDOMRuleArr.add(lRule);
				lRule.setRDFIdentifier();
				DOMInfoModel.masterDOMRuleMap.put(lRule.rdfIdentifier, lRule);
				lRule.xpath = lObject.context;
				lRule.roleId = roleWarning;
				lRule.attrTitle = lObject.title;		
				lRule.attrNameSpaceNC = lObject.classNameSpaceIdNC;		
				lRule.classTitle = lObject.className;		
				lRule.classNameSpaceNC = lObject.classNameSpaceIdNC;
				lRule.classSteward = DMDocument.masterNameSpaceIdNCLC;

			}
			String lAttrId = lRule.attrNameSpaceNC + ":" + lRule.attrTitle;
			DOMAssert lAssert = new DOMAssert (lAttrId);
			lRule.assertArr.add(lAssert);
			if (! lObject.isValue) {
				lAssert.assertStmt = "false()";	
				lAssert.assertMsg =  lObject.context + " is deprecated and should not be used.";
			} else {
				lAssert.assertStmt = lObject.classNameSpaceIdNC + ":" + lObject.attrName + " != '" + lObject.value + "'";	
				lAssert.assertMsg =  "The value " + lObject.value + " for attribute " + lObject.className + "." + lObject.attrName + " is deprecated and should not be used.";
			}
		} 
	}				
	
//	add the schematron rules for UnitId deprecation
	public void addSchematronRuleDeprecatedUnitId (SchemaFileDefn lSchemaFileDefn) {
		DeprecatedDefn lObject = null;
		String roleWarning = " role=\"warning\"";
		
		// iterate over all attributes and find those with units of measure
		for (Iterator <DOMAttr> i = DOMInfoModel.masterDOMAttrArr.iterator(); i.hasNext();) {
			DOMAttr lAttr = (DOMAttr) i.next();
			if (! lAttr.isAttribute) continue;
			if (lAttr.unit_of_measure_type.indexOf("TBD") == 0) continue;
			
			// write a rule for each deprecated unit value
			for (Iterator <DeprecatedDefn> j = lUnitIdDeprecatedArr.iterator(); j.hasNext();) {
				lObject = (DeprecatedDefn) j.next();
				if (lObject.className.compareTo(lAttr.unit_of_measure_type) == 0) {
					String lContext = lAttr.classNameSpaceIdNC + ":" + lAttr.parentClassTitle + "/" + lAttr.nameSpaceIdNC + ":" + lAttr.title;
					String lRuleId = lContext + roleWarning;
					DOMRule lRule = DOMInfoModel.masterDOMRuleIdMap.get(lRuleId);
					if (lRule == null) {
						lRule = new DOMRule(lRuleId);
						DOMInfoModel.masterDOMRuleIdMap.put(lRule.identifier, lRule);			
						DOMInfoModel.masterDOMRuleArr.add(lRule);
						lRule.setRDFIdentifier();
						DOMInfoModel.masterDOMRuleMap.put(lRule.rdfIdentifier, lRule);
						lRule.xpath = lContext;
						lRule.roleId = roleWarning;
						lRule.attrTitle = lAttr.title;		
						lRule.attrNameSpaceNC = lAttr.nameSpaceIdNC;		
						lRule.classTitle = lAttr.parentClassTitle;		
						lRule.classNameSpaceNC = lAttr.classNameSpaceIdNC;
						lRule.classSteward = DMDocument.masterNameSpaceIdNCLC;
					}
					String lAttrId = lRule.attrNameSpaceNC + ":" + lRule.attrTitle;
					DOMAssert lAssert = new DOMAssert (lAttrId);
					lRule.assertArr.add(lAssert);
					lAssert.assertStmt = "@unit != '" + lObject.value + "'";	
					lAssert.assertMsg =  "The unit value " + lObject.value + " is deprecated and should not be used.";
				}
			}
		} 
	}								
	
//	add the Discipline schematron rules
	public void addSchematronRuleDisciplineFacets (SchemaFileDefn lSchemaFileDefn, TreeMap <String, SFDisciplineFacetDefn> lDisciplineFacetMap) {
		// add attribute enumerated values assertions
		ArrayList <SFDisciplineFacetDefn> lDisciplineFacetArr = new ArrayList <SFDisciplineFacetDefn> (lDisciplineFacetMap.values());
		ArrayList <String> lDiscNameFacet1Arr = new ArrayList <String> ();
		ArrayList <String> lDiscNameFacet2Arr = new ArrayList <String> ();
		
		// set up the Primary Result Summary rule
		String lRuleId = "pds:Primary_Result_Summary/pds:Science_Facets";
		DOMRule lRule = DOMInfoModel.masterDOMRuleIdMap.get(lRuleId);
		lRule = new DOMRule(lRuleId);
		DOMInfoModel.masterDOMRuleIdMap.put(lRule.identifier, lRule);		
		DOMInfoModel.masterDOMRuleArr.add(lRule);
		lRule.setRDFIdentifier();
		DOMInfoModel.masterDOMRuleMap.put(lRule.rdfIdentifier, lRule);
		
		lRule.xpath = lRuleId;
		lRule.attrTitle = "discipline_name";		
		lRule.attrNameSpaceNC = DMDocument.masterNameSpaceIdNCLC;		
		lRule.classTitle = "Science_Facets";		
		lRule.classNameSpaceNC = DMDocument.masterNameSpaceIdNCLC;
		lRule.classSteward = DMDocument.masterNameSpaceIdNCLC;
		String lAttrId = lRule.attrNameSpaceNC + ":" + lRule.attrTitle;
		
		// create the discipline name rule
		if (lDisciplineFacetArr.size() > 0) {
			DOMAssert lAssert = new DOMAssert (lAttrId);
			lRule.assertArr.add(lAssert);
			lAssert.assertStmt = "if (pds:discipline_name) then pds:discipline_name" + " = (";	
			lAssert.assertMsg =  "The attribute pds:discipline_name must be equal to one of the following values ";
			String lValueList = "";
			String lDelimiter = "";
			for (Iterator <SFDisciplineFacetDefn> i = lDisciplineFacetArr.iterator(); i.hasNext();) {
				SFDisciplineFacetDefn lDiscFacet = (SFDisciplineFacetDefn) i.next();
				lValueList += lDelimiter + "'" + lDiscFacet.disciplineName + "'";
				lDelimiter = ", ";
			}
			lAssert.assertStmt += lValueList;	
			lAssert.assertMsg += lValueList;
			lAssert.assertStmt += ") else true()";	
			lAssert.assertMsg +=  ".";
		}
		
		// create assert statement for each discipline name
		for (Iterator <SFDisciplineFacetDefn> i = lDisciplineFacetArr.iterator(); i.hasNext();) {
			SFDisciplineFacetDefn lDiscFacet = (SFDisciplineFacetDefn) i.next();
			lRule.attrTitle = lDiscFacet.disciplineName;		
			lRule.attrNameSpaceNC = DMDocument.masterNameSpaceIdNCLC;
			
			// get all the facet1 assert statements
			if (lDiscFacet.groupFacet1Arr.size() > 0) {
				lDiscNameFacet1Arr.add(lDiscFacet.disciplineName); 
				DOMAssert lAssert = new DOMAssert (lAttrId);
				lAssert.assertStmt = "if (pds:discipline_name and pds:facet1 and (pds:discipline_name eq '" + lDiscFacet.disciplineName + "')) then " + "pds:facet1" + " = (";	
				lAssert.assertMsg =  "If the attribute pds:discipline_name equals " + lDiscFacet.disciplineName + " then if present pds:facet1 must be equal to one of the following values ";
				lRule.assertArr.add(lAssert);
				String lValueList = "";
				String lDelimiter = "";
				for (Iterator <SFGroupFacetDefn> j = lDiscFacet.groupFacet1Arr.iterator(); j.hasNext();) {
					SFGroupFacetDefn lGroupFacet = (SFGroupFacetDefn) j.next();	
					lValueList += lDelimiter + "'" + lGroupFacet.facet + "'";
					lDelimiter = ", ";
				}
				lAssert.assertStmt += lValueList;	
				lAssert.assertMsg += lValueList;
				lAssert.assertStmt += ") else true()";	
				lAssert.assertMsg +=  ".";
			}
			
			// get all the facet2 assert statements
			if (lDiscFacet.groupFacet2Arr.size() > 0) {
				lDiscNameFacet2Arr.add(lDiscFacet.disciplineName); 
				DOMAssert lAssert = new DOMAssert (lAttrId);
				lAssert.assertStmt = "if (pds:discipline_name and pds:facet2 and (pds:discipline_name eq '" + lDiscFacet.disciplineName + "')) then " + "pds:facet2" + " = (";	
				lAssert.assertMsg =  "If the attribute pds:discipline_name equals " + lDiscFacet.disciplineName + " then if present pds:facet2 must be equal to one of the following values ";
				lRule.assertArr.add(lAssert);
				String lValueList = "";
				String lDelimiter = "";
				for (Iterator <SFGroupFacetDefn> j = lDiscFacet.groupFacet2Arr.iterator(); j.hasNext();) {
					SFGroupFacetDefn lGroupFacet = (SFGroupFacetDefn) j.next();	
					lValueList += lDelimiter + "'" + lGroupFacet.facet + "'";
					lDelimiter = ", ";
				}
				lAssert.assertStmt += lValueList;	
				lAssert.assertMsg += lValueList;
				lAssert.assertStmt += ") else true()";	
				lAssert.assertMsg +=  ".";
			}
		} 
		
		// create the rules for disallowed facets
		int lNumDiscNames = lDisciplineFacetArr.size();
		if (lNumDiscNames > lDiscNameFacet1Arr.size()) {
			DOMAssert lAssert = new DOMAssert (lAttrId);
			lRule.assertArr.add(lAssert);
			lAssert.assertStmt = "if (pds:discipline_name and pds:facet1) then pds:discipline_name = (";	
			lAssert.assertMsg =  "Facet1 is allowed only when pds:discipline_name is one of the following ";
			// if (pds:discipline_name and pds:facet1) then pds:discipline_name = ('Fields', 'Particles') else true()
			// Facet1 is allowed only for pds:discipline_name equals 'Fields' or 'Particles'.
			String lValueList = "";
			String lDelimiter = "";
			for (Iterator <String> i = lDiscNameFacet1Arr.iterator(); i.hasNext();) {
				String lDiscName = (String) i.next();
				lValueList += lDelimiter + "'" + lDiscName + "'";
				lDelimiter = ", ";
			}
			lAssert.assertStmt += lValueList;	
			lAssert.assertMsg += lValueList;
			lAssert.assertStmt += ") else true()";	
			lAssert.assertMsg +=  ".";
		}

		if (lNumDiscNames > lDiscNameFacet2Arr.size()) {
			DOMAssert lAssert = new DOMAssert (lAttrId);
			lRule.assertArr.add(lAssert);
			lAssert.assertStmt = "if (pds:discipline_name and pds:facet2) then pds:discipline_name = (";	
			lAssert.assertMsg =  "Facet2 is allowed only when pds:discipline_name is one of the following ";
			String lValueList = "";
			String lDelimiter = "";
			for (Iterator <String> i = lDiscNameFacet2Arr.iterator(); i.hasNext();) {
				String lDiscName = (String) i.next();
				lValueList += lDelimiter + "'" + lDiscName + "'";
				lDelimiter = ", ";
			}
			lAssert.assertStmt += lValueList;	
			lAssert.assertMsg += lValueList;
			lAssert.assertStmt += ") else true()";	
			lAssert.assertMsg +=  ".";
		}
		
		// set up the Primary Result Summary rule
		lRuleId = "pds:Primary_Result_Summary/pds:Science_Facets/pds:subfacet1";
		lRule = DOMInfoModel.masterDOMRuleIdMap.get(lRuleId);
		lRule = new DOMRule(lRuleId);
		DOMInfoModel.masterDOMRuleIdMap.put(lRule.identifier, lRule);		
		DOMInfoModel.masterDOMRuleArr.add(lRule);
		lRule.setRDFIdentifier();
		DOMInfoModel.masterDOMRuleMap.put(lRule.rdfIdentifier, lRule);
		
		lRule.xpath = lRuleId;
		lRule.attrTitle = "subfacet1";		
		lRule.attrNameSpaceNC = DMDocument.masterNameSpaceIdNCLC;		
		lRule.classTitle = "Science_Facets";		
		lRule.classNameSpaceNC = DMDocument.masterNameSpaceIdNCLC;	
		lRule.classSteward = DMDocument.masterNameSpaceIdNCLC;
		lAttrId = lRule.attrNameSpaceNC + ":" + lRule.attrTitle;
		DOMAssert lAssert = new DOMAssert (lAttrId);
		lRule.assertArr.add(lAssert);
		lAssert.assertStmt = "name() = 'pds:Primary_Result_Summary/pds:Science_Facets/pds:subfacet1'";	
		lAssert.assertMsg =  "pds:subfacet1 should not be used. No values have been provided.";
		
		lRuleId = "pds:Primary_Result_Summary/pds:Science_Facets/pds:subfacet2";
		lRule = DOMInfoModel.masterDOMRuleIdMap.get(lRuleId);
		lRule = new DOMRule(lRuleId);
		DOMInfoModel.masterDOMRuleIdMap.put(lRule.identifier, lRule);		
		DOMInfoModel.masterDOMRuleArr.add(lRule);
		lRule.setRDFIdentifier();
		DOMInfoModel.masterDOMRuleMap.put(lRule.rdfIdentifier, lRule);
		
		lRule.xpath = lRuleId;
		lRule.attrTitle = "subfacet2";		
		lRule.attrNameSpaceNC = DMDocument.masterNameSpaceIdNCLC;		
		lRule.classTitle = "Science_Facets";		
		lRule.classNameSpaceNC = DMDocument.masterNameSpaceIdNCLC;	
		lRule.classSteward = DMDocument.masterNameSpaceIdNCLC;
		lAttrId = lRule.attrNameSpaceNC + ":" + lRule.attrTitle;
		lAssert = new DOMAssert (lAttrId);
		lRule.assertArr.add(lAssert);
		lAssert.assertStmt = "name() = 'pds:Primary_Result_Summary/pds:Science_Facets/pds:subfacet2'";	
		lAssert.assertMsg =  "pds:subfacet2 should not be used. No values have been provided.";		
	}	
	
//	add the enumerated unit schematron rules
	public void addSchematronRuleUnits (SchemaFileDefn lSchemaFileDefn, TreeMap <String, DOMClass> lMasterDOMClassMap) {	
		
		// add attribute unit values assertions
		ArrayList <DOMClass> lSortClassArr = new ArrayList <DOMClass> (lMasterDOMClassMap.values());
		for (Iterator <DOMClass> i = lSortClassArr.iterator(); i.hasNext();) {
			DOMClass lClass = (DOMClass) i.next();
			if (lClass == null) continue;
			if (! ((lSchemaFileDefn.nameSpaceIdNC.compareTo(lClass.nameSpaceIdNC) == 0) && lSchemaFileDefn.stewardArr.contains(lClass.steward))) continue;
			if (lClass.isUSERClass || lClass.isUnitOfMeasure || lClass.isDataType || lClass.isVacuous) continue;
			for (Iterator <DOMProp> j = lClass.ownedAttrArr.iterator(); j.hasNext();) {
				DOMProp lDOMProp = (DOMProp) j.next();		
				
				if (lDOMProp.hasDOMObject != null && lDOMProp.hasDOMObject instanceof DOMAttr) {
					DOMAttr lDOMAttr = (DOMAttr) lDOMProp.hasDOMObject;
					if (lDOMAttr.unit_of_measure_type == null || lDOMAttr.unit_of_measure_type.indexOf("TBD") == 0) continue;				
					String lUnitsValueString = lDOMAttr.getUnits(true);
					if (lUnitsValueString == null) continue;					
					String lRuleId = lClass.nameSpaceId + lClass.title + "/" + lClass.nameSpaceId + lDOMAttr.title;
					DOMRule lRule = DOMInfoModel.masterDOMRuleIdMap.get(lRuleId);
					if (lRule == null) {
						lRule = new DOMRule(lRuleId);		
						DOMInfoModel.masterDOMRuleIdMap.put(lRule.identifier, lRule);			
						DOMInfoModel.masterDOMRuleArr.add(lRule);
						lRule.setRDFIdentifier();
						DOMInfoModel.masterDOMRuleMap.put(lRule.rdfIdentifier, lRule);
						
						lRule.xpath = lRuleId;
						lRule.attrTitle = lDOMAttr.title;		
						lRule.attrNameSpaceNC = lDOMAttr.nameSpaceIdNC;		
						lRule.classTitle = lClass.title;		
						lRule.classNameSpaceNC = lClass.nameSpaceIdNC;	
						lRule.classSteward = lClass.steward;
					}
					String assertMsgPre = " must be equal to one of the following values "; 
					String lAttrId = lRule.attrNameSpaceNC + ":" + lRule.attrTitle;
					if (foundAssertStmt (lAttrId, lRule.assertArr)) continue;
					DOMAssert lAssert = new DOMAssert (lAttrId);
					lAssert.assertStmt = "@unit" + " = (";	
					lAssert.assertMsg =  "The attribute " + "@unit" + assertMsgPre;
					lRule.assertArr.add(lAssert);
					lAssert.assertStmt += lUnitsValueString + ")";	
					lAssert.assertMsg += lUnitsValueString + ".";
				}
			}
		} 
	}		
	
//	find offending rule
	public void findOffendingRule (String lTitle, ArrayList <DOMRule> lRuleArr) {
		for (Iterator <DOMRule> i = lRuleArr.iterator(); i.hasNext();) {
			DOMRule lRule = (DOMRule) i.next();	
			if (lRule.classTitle.indexOf("Discipline_Facets") > -1 || lRule.identifier.indexOf("Discipline_Facets") > -1) {
				System.out.println("debug findOffendingRule - lRule.identifier:" + lRule.identifier);
			}
		}
		return;
	}
	
//	check if assert statement already exists
	public boolean foundAssertStmt (String lAttrId, ArrayList <DOMAssert> lAssertArr) {
		for (Iterator <DOMAssert> i = lAssertArr.iterator(); i.hasNext();) {
			DOMAssert lAssert = (DOMAssert) i.next();	
			if (lAssert.identifier.compareTo(lAttrId) == 0) {
				return true;
			}
		}
		return false;
	}
}
