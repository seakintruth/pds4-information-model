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

/** Driver for getting document
 *
 */
public class ExportModels extends Object {
	boolean PDSOptionalFlag = false;
	
	public ExportModels () {
				
	}

/**********************************************************************************************************
		write the various documents and files
***********************************************************************************************************/

	public void writeAllArtifacts (boolean domFlag, boolean mofFlag) throws java.io.IOException {	    
	    // write the model specification
		//DOM
		if (domFlag) {
		WriteDOMSpecification writeDOMSpecification = new WriteDOMSpecification(DMDocument.docInfo, PDSOptionalFlag);
		writeDOMSpecification.printArtifacts();
		}
		
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Specification Done");
		
		if (DMDocument.exportJSONAttrFlag)  {
			WriteModelAttrJSONFile lWriteModelAttrJSONFile = new WriteModelAttrJSONFile ();
			lWriteModelAttrJSONFile.writeJSONFile (DMDocument.masterPDSSchemaFileDefn);
		}
		
		// get the schema file definitions
		ArrayList <SchemaFileDefn> lSchemaFileDefnArr = new ArrayList <SchemaFileDefn> (DMDocument.masterSchemaFileSortMap.values());
		
		//	write the label schema - new version 4		
		for (Iterator <SchemaFileDefn> i = lSchemaFileDefnArr.iterator(); i.hasNext();) {
			SchemaFileDefn lSchemaFileDefn = (SchemaFileDefn) i.next();
			if (! lSchemaFileDefn.isActive) continue;
			
			//	write the label schema			
			if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - XML Schema - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");
			// DOM
			if (domFlag) {
			XML4LabelSchemaDOM xml4LabelSchemaDOM = new XML4LabelSchemaDOM ();
			xml4LabelSchemaDOM.writeXMLSchemaFiles (lSchemaFileDefn, DOMInfoModel.masterDOMClassArr);
			}
			//  write schematron file
			//DOM
			if (domFlag) {
			WriteDOMSchematron writeDOMSchematron = new WriteDOMSchematron ();
			writeDOMSchematron.writeSchematronFile(lSchemaFileDefn, DOMInfoModel.masterDOMClassMap);
			}
			if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Schematron - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");
			
			//  write label file for XML Schema and Schematron
			WriteCoreXMLSchemaLabel writeCoreXMLSchemaLabel = new WriteCoreXMLSchemaLabel ();
			writeCoreXMLSchemaLabel.writeFile(lSchemaFileDefn);
			if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Schema Label - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");
		}	
		
	    // write the Doc Book
		if (domFlag) {
		WriteDOMDocBook lWriteDOMDocBook  = new WriteDOMDocBook (); 
		lWriteDOMDocBook.writeDocBook(DMDocument.masterPDSSchemaFileDefn);
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - DD DocBook Done");

		// write the xmi file
		//DOM
		if (domFlag) {
		XMI2LabelSchemaDOM xmi2LabelSchemaDOM = new XMI2LabelSchemaDOM ();
		xmi2LabelSchemaDOM.getXMIElements ();
		xmi2LabelSchemaDOM.writeXMIFile (DMDocument.sTodaysDate);	
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - XMI1 Done");

		// write the xmi file - original version with relationship names
		if (domFlag) {
//		XMI2LabelSchema2 xmi2LabelSchema2 = new XMI2LabelSchema2 (); *** deprecated ***
//		xmi2LabelSchema2.writeXMIFile (DMDocument.sTodaysDate);
		XMI2LabelSchemaWNamesDOM xmi2LabelSchemaWNames = new XMI2LabelSchemaWNamesDOM ();
		xmi2LabelSchemaWNames.writeXMIFile (DMDocument.sTodaysDate);
		}
		
		// DOM  write the xmi file - original version with relationship names
		if (domFlag) {
		XMI2LabelSchema2DOM xmi2LabelSchema2DOM = new XMI2LabelSchema2DOM ();
		xmi2LabelSchema2DOM.writeXMIFile (DMDocument.sTodaysDate);
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - XMI2 Done");
		
		// write the RDF
		// write the DOM RDF
		if (domFlag) {
		WriteDOM11179DDRDFFile writeDOM11179DDRDFFile = new WriteDOM11179DDRDFFile ();
		writeDOM11179DDRDFFile.printISO11179DDRDF (DMDocument.sTodaysDate);
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - RDF Done");
		
		// write the IM to RDF
//		WriteDOMIMToRDF lWriteDOMIMToRDF = new WriteDOMIMToRDF ();
//		lWriteDOMIMToRDF.domIMToRDFWriter (DMDocument.sTodaysDate);

		// write the Lucid Ingest file
//		WriteLucidMySQLFiles WriteLucidFiles = new WriteLucidMySQLFiles ();
//		WriteLucidFiles.WriteLucidFile();
		
		// write the DOM PDS4 DD CSV file
    	if (domFlag) {
	    WriteDOMCSVFiles writeDOMCSVFiles = new WriteDOMCSVFiles (); 
		ArrayList <DOMClass> domSortClassArr = new ArrayList <DOMClass> (DOMInfoModel.masterDOMClassMap.values());
		writeDOMCSVFiles.writeDOMCSVFile (domSortClassArr, DMDocument.masterPDSSchemaFileDefn, null);
    	}
        if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - DD CSV Done");
        
		// write the PDS4 CCSDS CSV file 
//		WriteDocCSV writeDocCSV = new WriteDocCSV ();
//		writeDocCSV.writeDocCSV (DMDocument.masterPDSSchemaFileDefn);
//		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - CCSDS CSV Done");
		
		//DOM
		if (domFlag) {
		WriteDOM11179DDPinsFile lWriteDOM11179DDPinsFile = new WriteDOM11179DDPinsFile ();
		lWriteDOM11179DDPinsFile.writePINSFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecDDProtPins);	
		lWriteDOM11179DDPinsFile.writePINSFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecDDProtPinsSN);	
		}
		
		// write the 11179 DD pins file
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - DD Pins File Done");
		
		// write the 11179 DOM JSON file - requires DOMInfoModel to be executed
		if (domFlag) {
			WriteDOMDDJSONFile writeDOMDDJSONFile = new WriteDOMDDJSONFile ();
			writeDOMDDJSONFile.writeJSONFile ();
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - JSON Done");
		
		// write the LOD SKOS file
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - SKOS Done");
		
		//DOM
		if (domFlag) {
		WriteLODSKOSFileDOM writeLODSKOSDOMFile = new WriteLODSKOSFileDOM ();
		writeLODSKOSDOMFile.writeDOMSKOSFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecSKOSTTL_DOM);
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - SKOS Done");

		// write the RDF/OWL file
		if (! DMDocument.LDDToolFlag) {
			//DOM
			if (domFlag) {
				WriteDOMRDFOWLFile writeDOMRDFOWLFile = new WriteDOMRDFOWLFile ();
				writeDOMRDFOWLFile.writeOWLFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecOWLRDF_DOM);	
			}
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - RDF/OWL Done");
		
		// write the 11179 DD Data Element Definition XML Files
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Class Defn Done");
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Attr Defn Done");

		//DOM
		// write the 11179 DOM DD Data Element Definition XML Files
		if (domFlag) {
		WriteDDProductDOMAttrDefinitions writeDDProductDOMAttrDefinitions = new WriteDDProductDOMAttrDefinitions ();
		writeDDProductDOMAttrDefinitions.writeDDDOMRegFiles (DMDocument.masterPDSSchemaFileDefn, DMDocument.sTodaysDate);
		
		// write the 11179 DOM DD Class Definition XML Files
		WriteDDProductDOMClassDefinitions writeDDProductDOMClassDefinitions = new WriteDDProductDOMClassDefinitions ();
		writeDDProductDOMClassDefinitions.writeDDProductDOMClassDefnFiles(DMDocument.masterPDSSchemaFileDefn, DMDocument.sTodaysDate);
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllDOMArtifacts - DOM Class Defn Done");
		if (DMDocument.debugFlag) System.out.println("debug writeAllDOMArtifacts - DOM Attr Defn Done");
		
		// write the registry configuration files *** Reg needs conversion.
		if (false) {
		RegConfig regConfig = new RegConfig ();
		regConfig.writeRegRIM(DMDocument.sTodaysDate);
		regConfig.writeRegRIM3(DMDocument.sTodaysDate);
		regConfig.writeRegRIM4(DMDocument.sTodaysDate);
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Regisry Config Done");
		
		// write the standard id extract file 
		// DOM
		if (domFlag) {
		WriteDOMStandardIdExtract writeDOMStandardIdExtract = new WriteDOMStandardIdExtract ();
        writeDOMStandardIdExtract.writeExtractFile();
		}
		if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Standard Id Done");
		
		// print out the histogram for the DEC concepts
/*		System.out.println("\nConcept Histogram");
		Set <String> set1 = MasterDOMInfoModel.metricConceptMap.keySet();
		Iterator <String> iter1 = set1.iterator();
		while(iter1.hasNext()) {
			String lId = (String) iter1.next();
			Integer lCount = MasterDOMInfoModel.metricConceptMap.get(lId);
			System.out.println("Descriptor: " + lId + "    Count: " + lCount);
		} */	
		return;
	}

	public void writeLDDArtifacts (boolean domFlag, boolean mofFlag) throws java.io.IOException {
		// DOM
		ArrayList <DOMClass> lLDDDOMClassArr = new ArrayList <DOMClass> ();
		TreeMap <String, DOMClass> lLDDDOMClassMap = new TreeMap <String, DOMClass> ();
		
		// get LDD Classes
		// DOM
		if (domFlag) {
		ArrayList <DOMClass> lClassArr = new ArrayList <DOMClass> (DOMInfoModel.masterDOMClassMap.values());
		for (Iterator <DOMClass> i = lClassArr.iterator(); i.hasNext();) {
			DOMClass lClass = (DOMClass) i.next();
			if (lClass.isFromLDD) {
				if (lClass.allAttrAssocArr.size() != 0) {
					lLDDDOMClassArr.add(lClass);
					lLDDDOMClassMap.put(lClass.title, lClass);
				}
			}
		}
		}
		
	    // write the Doc Book - includes common and all stacked LDDs
		if (DMDocument.exportDDFileFlag) {				
			WriteDOMDocBook lWriteDOMDocBook  = new WriteDOMDocBook (); 
			lWriteDOMDocBook.writeDocBook(DMDocument.masterPDSSchemaFileDefn);				
			if (DMDocument.debugFlag) System.out.println("debug writeLDDArtifacts - DD DocBook Done");
		}
		
		// get the LDD SchemaFileDefn - should be just one; but the Master must be skipped
		ArrayList <SchemaFileDefn> lSchemaFileDefnArr = new ArrayList <SchemaFileDefn> (DMDocument.masterSchemaFileSortMap.values());
		for (Iterator <SchemaFileDefn> i = lSchemaFileDefnArr.iterator(); i.hasNext();) {
			SchemaFileDefn lSchemaFileDefn = (SchemaFileDefn) i.next();
			
			if (! lSchemaFileDefn.isActive) continue;
			
			// skip the master for LDD runs
			if (lSchemaFileDefn.isMaster) continue;
			
			// DOM
			if (domFlag) {
			//	write the schema - new version 4
			XML4LabelSchemaDOM xml4LabelSchemaDOM = new XML4LabelSchemaDOM ();
			xml4LabelSchemaDOM.writeXMLSchemaFiles (lSchemaFileDefn, lLDDDOMClassArr);
			if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - XML Schema - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");
			
			//  write schematron file
			WriteDOMSchematron writeDOMSchematron = new WriteDOMSchematron ();
			writeDOMSchematron.writeSchematronFile(lSchemaFileDefn, lLDDDOMClassMap);
			if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Schematron - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");

			//  write label file for XML Schema and Schematron
			WriteCoreXMLSchemaLabel writeCoreXMLSchemaLabel = new WriteCoreXMLSchemaLabel ();
			writeCoreXMLSchemaLabel.writeFile(lSchemaFileDefn);
			if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - Schema Label - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");

			// write the 11179 JSON file
//??
			if (DMDocument.exportJSONFileFlag) {
//				Write11179DDJSONFile write11179DDJSONFile = new Write11179DDJSONFile ();
//				write11179DDJSONFile.writeJSONFile (lSchemaFileDefn);
				WriteDOMDDJSONFile writeDOMDDJSONFile = new WriteDOMDDJSONFile ();
				writeDOMDDJSONFile.writeJSONFile ();
				if (DMDocument.debugFlag) System.out.println("debug writeAllArtifacts - JSON Done");
			}

			// write the Info Spec file 
			if (DMDocument.exportSpecFileFlag) {
				WriteDOMSpecification writeDOMSpecification = new WriteDOMSpecification (DMDocument.docInfo, PDSOptionalFlag); 
				writeDOMSpecification.printArtifacts();
				if (DMDocument.debugFlag) System.out.println("debug writeLDDArtifacts - Info Model Spec Done");
			}
			}

			System.out.println(">>info    - LDDTOOL Exit");
		}
		return;
	}
}
