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

	public void writeAllArtifacts (boolean domFlagxxx, boolean mofFlag) throws java.io.IOException {	    
	    // write the model specification
		WriteDOMSpecification writeDOMSpecification = new WriteDOMSpecification(DMDocument.docInfo, PDSOptionalFlag);
		writeDOMSpecification.printArtifacts();
		
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Specification Done");
		
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
			DMDocument.registerMessage ("0>info " + "writeAllArtifacts - XML Schema - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");
			XML4LabelSchemaDOM xml4LabelSchemaDOM = new XML4LabelSchemaDOM ();
			xml4LabelSchemaDOM.writeXMLSchemaFiles (lSchemaFileDefn, DOMInfoModel.masterDOMClassArr);
			
			//  write schematron file
			WriteDOMSchematron writeDOMSchematron = new WriteDOMSchematron ();
			writeDOMSchematron.writeSchematronFile(lSchemaFileDefn, DOMInfoModel.masterDOMClassMap);
			DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Schematron - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");
			
			//  write label file for XML Schema and Schematron
			WriteCoreXMLSchemaLabel writeCoreXMLSchemaLabel = new WriteCoreXMLSchemaLabel ();
			writeCoreXMLSchemaLabel.writeFile(lSchemaFileDefn);
			DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Schema Label - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");
		}	
		
	    // write the Doc Book
		WriteDOMDocBook lWriteDOMDocBook  = new WriteDOMDocBook (); 
		lWriteDOMDocBook.writeDocBook(DMDocument.masterPDSSchemaFileDefn);
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - DD DocBook Done");
		
		// write the new xmi file
		WriteUML25XMIFile lWriteUML25XMIFile = new WriteUML25XMIFile ();
		lWriteUML25XMIFile.writeXMIFile (DMDocument.sTodaysDate);
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - UML 25 XMI File Done");
		
		// write the new Neo4J file
//		WriteNeo4J lWriteNeo4J = new WriteNeo4J ();
//		lWriteNeo4J.writeXMIFile (DMDocument.sTodaysDate);
//		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Neo4J File Done");
		
		// write the RDF
		// write the DOM RDF
		WriteDOM11179DDRDFFile writeDOM11179DDRDFFile = new WriteDOM11179DDRDFFile ();
		writeDOM11179DDRDFFile.printISO11179DDRDF (DMDocument.sTodaysDate);
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - RDF Done");
		
		// write the IM to RDF
//		WriteDOMIMToRDF lWriteDOMIMToRDF = new WriteDOMIMToRDF ();
//		lWriteDOMIMToRDF.domIMToRDFWriter (DMDocument.sTodaysDate);

		// write the Lucid Ingest file
//		WriteLucidMySQLFiles WriteLucidFiles = new WriteLucidMySQLFiles ();
//		WriteLucidFiles.WriteLucidFile();
		
		// write the DOM PDS4 DD CSV file
	    WriteDOMCSVFiles writeDOMCSVFiles = new WriteDOMCSVFiles (); 
		ArrayList <DOMClass> domSortClassArr = new ArrayList <DOMClass> (DOMInfoModel.masterDOMClassMap.values());
		writeDOMCSVFiles.writeDOMCSVFile (domSortClassArr, DMDocument.masterPDSSchemaFileDefn, null);
        DMDocument.registerMessage ("0>info " + "writeAllArtifacts - DD CSV Done");
        
		// write the PDS4 CCSDS CSV file 
//		WriteDocCSV writeDocCSV = new WriteDocCSV ();
//		writeDocCSV.writeDocCSV (DMDocument.masterPDSSchemaFileDefn);
//		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - CCSDS CSV Done");
		
		WriteDOM11179DDPinsFile lWriteDOM11179DDPinsFile = new WriteDOM11179DDPinsFile ();
		lWriteDOM11179DDPinsFile.writePINSFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecDDProtPins);	
		lWriteDOM11179DDPinsFile.writePINSFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecDDProtPinsSN);	
		
		// write the 11179 DD pins file
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - DD Pins File Done");
		
		// write the 11179 DOM JSON file - requires DOMInfoModel to be executed
			WriteDOMDDJSONFile writeDOMDDJSONFile = new WriteDOMDDJSONFile ();
			writeDOMDDJSONFile.writeJSONFile ();
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - JSON Done");
		
		// write the LOD SKOS file
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - SKOS Done");
		
		WriteLODSKOSFileDOM writeLODSKOSDOMFile = new WriteLODSKOSFileDOM ();
		writeLODSKOSDOMFile.writeDOMSKOSFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecSKOSTTL_DOM);
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - SKOS Done");

		// write the RDF/OWL file
		if (! DMDocument.LDDToolFlag) {
				WriteDOMRDFOWLFile writeDOMRDFOWLFile = new WriteDOMRDFOWLFile ();
				writeDOMRDFOWLFile.writeOWLFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecOWLRDF_DOM);	
		}
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - RDF/OWL Done");
		
		// write the 11179 DD Data Element Definition XML Files
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Class Defn Done");
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Attr Defn Done");
		
		// write the 11179 DOM DD Data Element Definition XML Files
		WriteDDProductDOMAttrDefinitions writeDDProductDOMAttrDefinitions = new WriteDDProductDOMAttrDefinitions ();
		writeDDProductDOMAttrDefinitions.writeDDDOMRegFiles (DMDocument.masterPDSSchemaFileDefn, DMDocument.sTodaysDate);
		
		// write the 11179 DOM DD Class Definition XML Files
		WriteDDProductDOMClassDefinitions writeDDProductDOMClassDefinitions = new WriteDDProductDOMClassDefinitions ();
		writeDDProductDOMClassDefinitions.writeDDProductDOMClassDefnFiles(DMDocument.masterPDSSchemaFileDefn, DMDocument.sTodaysDate);
		DMDocument.registerMessage ("0>info " + "writeAllDOMArtifacts - DOM Class Defn Done");
		DMDocument.registerMessage ("0>info " + "writeAllDOMArtifacts - DOM Attr Defn Done");
		
		// write the registry configuration files *** Reg needs conversion.
		if (false) {
		RegConfig regConfig = new RegConfig ();
		regConfig.writeRegRIM(DMDocument.sTodaysDate);
		regConfig.writeRegRIM3(DMDocument.sTodaysDate);
		regConfig.writeRegRIM4(DMDocument.sTodaysDate);
		}
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Regisry Config Done");
		
		// write the standard id extract file 
		WriteDOMStandardIdExtract writeDOMStandardIdExtract = new WriteDOMStandardIdExtract ();
        writeDOMStandardIdExtract.writeExtractFile();
		DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Standard Id Done");
		
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

	public void writeLDDArtifacts (boolean domFlagxxx, boolean mofFlag) throws java.io.IOException {
		// DOM
		ArrayList <DOMClass> lLDDDOMClassArr = new ArrayList <DOMClass> ();
		TreeMap <String, DOMClass> lLDDDOMClassMap = new TreeMap <String, DOMClass> ();
		
		// get LDD Classes
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
		
	    // write the Doc Book - includes common and all stacked LDDs
		if (DMDocument.exportDDFileFlag) {				
			WriteDOMDocBook lWriteDOMDocBook  = new WriteDOMDocBook (); 
			lWriteDOMDocBook.writeDocBook(DMDocument.masterPDSSchemaFileDefn);				
			DMDocument.registerMessage ("0>info " + "writeLDDArtifacts - DD DocBook Done");
		}
		
		// write the OWL File
		if (DMDocument.exportOWLFileFlag) {
			WriteDOMRDFOWLFile writeDOMRDFOWLFile = new WriteDOMRDFOWLFile ();
			writeDOMRDFOWLFile.writeOWLFile (DMDocument.masterPDSSchemaFileDefn.relativeFileSpecOWLRDF_DOM);
			DMDocument.registerMessage ("0>info " + "writeLDDArtifacts - OWL File Done");
		}
		
		// get the LDD SchemaFileDefn - should be just one; but the Master must be skipped
		ArrayList <SchemaFileDefn> lSchemaFileDefnArr = new ArrayList <SchemaFileDefn> (DMDocument.masterSchemaFileSortMap.values());
		for (Iterator <SchemaFileDefn> i = lSchemaFileDefnArr.iterator(); i.hasNext();) {
			SchemaFileDefn lSchemaFileDefn = (SchemaFileDefn) i.next();
			
			if (! lSchemaFileDefn.isActive) continue;
			
			// skip the master for LDD runs
			if (lSchemaFileDefn.isMaster) continue;
			
			//	write the schema - new version 4
			XML4LabelSchemaDOM xml4LabelSchemaDOM = new XML4LabelSchemaDOM ();
			xml4LabelSchemaDOM.writeXMLSchemaFiles (lSchemaFileDefn, lLDDDOMClassArr);
			DMDocument.registerMessage ("0>info " + "writeAllArtifacts - XML Schema - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");
			
			//  write schematron file
			WriteDOMSchematron writeDOMSchematron = new WriteDOMSchematron ();
			writeDOMSchematron.writeSchematronFile(lSchemaFileDefn, lLDDDOMClassMap);
			DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Schematron - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");

			//  write label file for XML Schema and Schematron
			WriteCoreXMLSchemaLabel writeCoreXMLSchemaLabel = new WriteCoreXMLSchemaLabel ();
			writeCoreXMLSchemaLabel.writeFile(lSchemaFileDefn);
			DMDocument.registerMessage ("0>info " + "writeAllArtifacts - Schema Label - lSchemaFileDefn.identifier:" + lSchemaFileDefn.identifier + " - Done");

			// write the 11179 JSON file
			if (DMDocument.exportJSONFileFlag || DMDocument.exportJSONFileAllFlag) {
				WriteDOMDDJSONFile writeDOMDDJSONFile = new WriteDOMDDJSONFile ();
				writeDOMDDJSONFile.writeJSONFile ();
				DMDocument.registerMessage ("0>info " + "writeAllArtifacts - JSON Done");
			}

			// write the Info Spec file 
			if (DMDocument.exportSpecFileFlag) {
				WriteDOMSpecification writeDOMSpecification = new WriteDOMSpecification (DMDocument.docInfo, PDSOptionalFlag); 
				writeDOMSpecification.printArtifacts();
				DMDocument.registerMessage ("0>info " + "writeLDDArtifacts - Info Model Spec Done");
			}
		}
		return;
	}
}
