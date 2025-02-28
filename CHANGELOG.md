# Changelog

## [v12.0.3](https://github.com/NASA-PDS/pds4-information-model/tree/v12.0.3) (2020-12-23)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v12.0.2...v12.0.3)

**Improvements:**

- CCB-285:  GeoTIFF format as operational PDS4 image [\#166](https://github.com/NASA-PDS/pds4-information-model/issues/166) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]

## [v12.0.2](https://github.com/NASA-PDS/pds4-information-model/tree/v12.0.2) (2020-12-22)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v12.0.1...v12.0.2)

**Defects:**

- LDDTool forces use of LDD versions based upon config [\#271](https://github.com/NASA-PDS/pds4-information-model/issues/271) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]

**Other closed issues:**

- CCB-317: Add FITS 4.0 to parsing\_standard\_id enumerated values for Header object [\#273](https://github.com/NASA-PDS/pds4-information-model/issues/273) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]

## [v12.0.1](https://github.com/NASA-PDS/pds4-information-model/tree/v12.0.1) (2020-12-18)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/12.0.0...v12.0.1)

**Improvements:**

- CCB-313: Definition of \<external\_source\_product\_identifier\> refers to non-existent documentation [\#253](https://github.com/NASA-PDS/pds4-information-model/issues/253) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- CCB-304: Cleanup unused Vector classes in IM before 2.0.0.0 [\#252](https://github.com/NASA-PDS/pds4-information-model/issues/252) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- Improvements from Build 11.0 testing [\#241](https://github.com/NASA-PDS/pds4-information-model/issues/241)

**Defects:**

- Incorrect error messages for DD\_Associate\_External\_Class [\#235](https://github.com/NASA-PDS/pds4-information-model/issues/235) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]
- PDS4\_PDS\_1E00.sch inconsistent rules for type of Investigation vs Investigation\_Area [\#186](https://github.com/NASA-PDS/pds4-information-model/issues/186) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]
- LDDTool: Displaying invalid Imaging Discipline Classes [\#175](https://github.com/NASA-PDS/pds4-information-model/issues/175) [[low](https://github.com/NASA-PDS/pds4-information-model/labels/low)]
- LDDTool: DocBook generation does not work from any file system location [\#170](https://github.com/NASA-PDS/pds4-information-model/issues/170) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]

**Other closed issues:**

- Stage the IMTool system for V1.16.0.0 to allow the implementation of approved SCRs and bug fixes. [\#262](https://github.com/NASA-PDS/pds4-information-model/issues/262)
- \[namespace-registry\] add new namespace "\<chan1\>" [\#245](https://github.com/NASA-PDS/pds4-information-model/issues/245) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- \[namespace-registry\] add new namespace "SURVEY" [\#234](https://github.com/NASA-PDS/pds4-information-model/issues/234)
- \[namespace-registry\] add new namespace "NUCSPEC" [\#233](https://github.com/NASA-PDS/pds4-information-model/issues/233)
- LDDTool: Enable to ability to set custom namespace base URI in IngestLDD   [\#104](https://github.com/NASA-PDS/pds4-information-model/issues/104)

## [12.0.0](https://github.com/NASA-PDS/pds4-information-model/tree/12.0.0) (2020-10-12)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/11.3.2...12.0.0)

**Improvements:**

- CCB-297: Inappropriate values in permissible values list for Product\_Observational/Observation\_Area/Target\_Identification/Internal\_Reference/reference\_type [\#220](https://github.com/NASA-PDS/pds4-information-model/issues/220)
- CCB-294: Add enumerated value to Units\_of\_Pixel\_Resolution\_Angular [\#218](https://github.com/NASA-PDS/pds4-information-model/issues/218)
- CCB-296: Duplicated value in enumerated value list of Product\_Context /Internal\_Reference /reference\_type. [\#216](https://github.com/NASA-PDS/pds4-information-model/issues/216)
- CCB-292:	New enumerated values for reference\_type. [\#214](https://github.com/NASA-PDS/pds4-information-model/issues/214)
- CCB-308: IM schematron needs to be updated to handle SI unit prefixes [\#193](https://github.com/NASA-PDS/pds4-information-model/issues/193) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]
- LDDTool: Use sch:value-of to display a variable in Schematron validation [\#167](https://github.com/NASA-PDS/pds4-information-model/issues/167) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- CCB-284: Streamline process for adding or removing standard values. [\#165](https://github.com/NASA-PDS/pds4-information-model/issues/165) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]

**Defects:**

- LDDTool: update LDD attribute checking to only check for "type" and "\_type" [\#230](https://github.com/NASA-PDS/pds4-information-model/issues/230) [[low](https://github.com/NASA-PDS/pds4-information-model/labels/low)]
- LDDTool: uses wrong version of XSD when referencing IMG dictionary [\#229](https://github.com/NASA-PDS/pds4-information-model/issues/229) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]
- LDDTool: erroneously replaces TBD values with null in JSON output [\#227](https://github.com/NASA-PDS/pds4-information-model/issues/227) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]

**Other closed issues:**

- lddtool-11.4.0-SNAPSHOT version output incorrect [\#228](https://github.com/NASA-PDS/pds4-information-model/issues/228)
- IMTool - Cleanup warning messages [\#225](https://github.com/NASA-PDS/pds4-information-model/issues/225)
- LDDTool: Revise the handling of supplying the IM version \(alternate version\) via command-line [\#222](https://github.com/NASA-PDS/pds4-information-model/issues/222)
- IMTool: Remove read of environment variable JAVA\_HOME [\#209](https://github.com/NASA-PDS/pds4-information-model/issues/209)

## [11.3.2](https://github.com/NASA-PDS/pds4-information-model/tree/11.3.2) (2020-08-17)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v11.3.1...11.3.2)

**Improvements:**

- CCB-289: Define new class Encoded\_Audio to describe supplementary audio data products [\#207](https://github.com/NASA-PDS/pds4-information-model/issues/207)
- CCB-290: Add value 'Field Campaign' to Investigation\_Area type enumerated value list [\#203](https://github.com/NASA-PDS/pds4-information-model/issues/203)
- LDDTool: Add capability to generate LDDs based on user-specified IM version [\#88](https://github.com/NASA-PDS/pds4-information-model/issues/88) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- LDDTool: Enhance software error handling and logging for improved usability [\#87](https://github.com/NASA-PDS/pds4-information-model/issues/87)

**Defects:**

- LDDtool combining two consecutive choice blocks into one block  [\#192](https://github.com/NASA-PDS/pds4-information-model/issues/192) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]
- LDDTool: requires one class with \(element\_flag = true\), even when no classes defined [\#188](https://github.com/NASA-PDS/pds4-information-model/issues/188) [[medium](https://github.com/NASA-PDS/pds4-information-model/labels/medium)]

**Other closed issues:**

- LDDTool: Add the alternate Information Model Version 1E00 [\#205](https://github.com/NASA-PDS/pds4-information-model/issues/205)
- LDDTool: System exit in code aborts MAVEN build [\#199](https://github.com/NASA-PDS/pds4-information-model/issues/199)
- LDDTool: Deprecation Rule being propogated to schematron file with an error in the assert statement. [\#197](https://github.com/NASA-PDS/pds4-information-model/issues/197)
- LDDTool: MSN namespace declaration is missing from the generated schematron file. [\#194](https://github.com/NASA-PDS/pds4-information-model/issues/194)
- LDDTool: Improve error messages to clearly designate between FATAL\_ERROR vs ERROR [\#178](https://github.com/NASA-PDS/pds4-information-model/issues/178)
- LDDTool: Add error counter or flag to enable unsuccessful termination System.exit [\#176](https://github.com/NASA-PDS/pds4-information-model/issues/176) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- LDDTool: Better handle LDD generation errors through more clear logging [\#86](https://github.com/NASA-PDS/pds4-information-model/issues/86)
- LDDTool: Cleanup output log messages to use consistent formatting and remove unnecessary messages [\#85](https://github.com/NASA-PDS/pds4-information-model/issues/85)

## [v11.3.1](https://github.com/NASA-PDS/pds4-information-model/tree/v11.3.1) (2020-05-22)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v11.3.0...v11.3.1)

## [v11.3.0](https://github.com/NASA-PDS/pds4-information-model/tree/v11.3.0) (2020-05-22)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v11.2.2...v11.3.0)

**Improvements:**

- LDDTool: Setup and configure CI/CD and Nightly Builds [\#158](https://github.com/NASA-PDS/pds4-information-model/issues/158) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- CCB-268 how to test this [\#143](https://github.com/NASA-PDS/pds4-information-model/issues/143)

**Defects:**

- LDDTool does not handle "unbounded" maximum cardinality in Choice blocks correctly [\#180](https://github.com/NASA-PDS/pds4-information-model/issues/180)

**Other closed issues:**

- LDDTool: Add PDS Namespace Registry information to Help output [\#172](https://github.com/NASA-PDS/pds4-information-model/issues/172) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- CCB-256: Need method for providing permissible value definitions for external namespaces in Ingest\_LDD [\#130](https://github.com/NASA-PDS/pds4-information-model/issues/130)

## [v11.2.2](https://github.com/NASA-PDS/pds4-information-model/tree/v11.2.2) (2020-04-06)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v11.2.1...v11.2.2)

**Defects:**

- LDDTool: Version info bug introduced since v11.2.0 [\#168](https://github.com/NASA-PDS/pds4-information-model/issues/168) [[low](https://github.com/NASA-PDS/pds4-information-model/labels/low)]

## [v11.2.1](https://github.com/NASA-PDS/pds4-information-model/tree/v11.2.1) (2020-03-24)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v11.2.0...v11.2.1)

## [v11.2.0](https://github.com/NASA-PDS/pds4-information-model/tree/v11.2.0) (2020-03-13)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v11.1.0...v11.2.0)

**Improvements:**

- CCB-279: Mis-Matched \<axes\> and Axis\_Array Specifications  [\#152](https://github.com/NASA-PDS/pds4-information-model/issues/152)
- CCB-272: Reinstate Array\_1D in the Information Model [\#148](https://github.com/NASA-PDS/pds4-information-model/issues/148)

**Defects:**

- Bugfix - Add code to fail gracefully for deprecated -M argument [\#154](https://github.com/NASA-PDS/pds4-information-model/issues/154)
- Bugfix - Change the error message reported for "nillable" attribute error. [\#146](https://github.com/NASA-PDS/pds4-information-model/issues/146)
- LDDtool: exposed class has a component class with a component exposed class [\#142](https://github.com/NASA-PDS/pds4-information-model/issues/142)

**Other closed issues:**

- LDDtool: unescaped & in output .xml [\#141](https://github.com/NASA-PDS/pds4-information-model/issues/141)

## [v11.1.0](https://github.com/NASA-PDS/pds4-information-model/tree/v11.1.0) (2020-03-11)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v11.0.0...v11.1.0)

**Improvements:**

- CCB-274 - Add attribute dictionary\_type to Ingest\_LDD - Update [\#144](https://github.com/NASA-PDS/pds4-information-model/issues/144)

## [v11.0.0](https://github.com/NASA-PDS/pds4-information-model/tree/v11.0.0) (2020-02-29)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v10.1.2...v11.0.0)

**Improvements:**

- CCB-278: Fix errors in logical\_identifier, ASCII\_LID, ASCIIVID and ASCII\_LIDVID\_LID [\#139](https://github.com/NASA-PDS/pds4-information-model/issues/139)
- CCB-274 - Add attribute dictionary\_type to Ingest\_LDD [\#137](https://github.com/NASA-PDS/pds4-information-model/issues/137)

**Other closed issues:**

- CCB-220: Add ability to specify many source products via table. [\#135](https://github.com/NASA-PDS/pds4-information-model/issues/135)
- CCB-271: Add reference\_types for Product\_Ancillary [\#133](https://github.com/NASA-PDS/pds4-information-model/issues/133)
- Improve versioning documentation to include IM version information [\#132](https://github.com/NASA-PDS/pds4-information-model/issues/132)

## [v10.1.2](https://github.com/NASA-PDS/pds4-information-model/tree/v10.1.2) (2020-01-24)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v10.1.1...v10.1.2)

**Defects:**

- LDDTool: Fix invalid examples or link to valid versions online [\#105](https://github.com/NASA-PDS/pds4-information-model/issues/105) [[low](https://github.com/NASA-PDS/pds4-information-model/labels/low)]
- Improve error handling for LDDTool processing of CTLI IngestLDD file [\#91](https://github.com/NASA-PDS/pds4-information-model/issues/91) [[low](https://github.com/NASA-PDS/pds4-information-model/labels/low)]

**Other closed issues:**

- Sync up LDDTool version with Maven build version [\#127](https://github.com/NASA-PDS/pds4-information-model/issues/127)
- Nillable attributes are not declared nillable in class definitions.   [\#125](https://github.com/NASA-PDS/pds4-information-model/issues/125)
- Sync LDDTool version with Maven version [\#124](https://github.com/NASA-PDS/pds4-information-model/issues/124)
- CCB-204: Define and enforce best practices for discipline and project dictionaries. Part-4 [\#122](https://github.com/NASA-PDS/pds4-information-model/issues/122)
- CCB-204: Define and enforce best practices for discipline and project dictionaries. Part-3 [\#113](https://github.com/NASA-PDS/pds4-information-model/issues/113)
- LDDTool aborts on short filename [\#111](https://github.com/NASA-PDS/pds4-information-model/issues/111)
- Clean up IMTool/LDDTool UML/XMI file writer for MagicDraw UML Class Diagrams [\#109](https://github.com/NASA-PDS/pds4-information-model/issues/109)
- CCB-138 Fix mismatch between context object types and values of \<type\> in \<Observing\_System\_Component\> class [\#103](https://github.com/NASA-PDS/pds4-information-model/issues/103)
- CCB-204: Validate that no attribute is named "unit" - Part 2 [\#101](https://github.com/NASA-PDS/pds4-information-model/issues/101)
- CCB-268 Add optional attribute to class Terminological\_Entry [\#99](https://github.com/NASA-PDS/pds4-information-model/issues/99)
- CCB-204: Define and enforce best practices for discipline and project dictionaries. - Part 1 [\#97](https://github.com/NASA-PDS/pds4-information-model/issues/97)
- Multi LDD DataDictionary Cleanup [\#95](https://github.com/NASA-PDS/pds4-information-model/issues/95)
- Update LDDTool to write the PDS4 Data Dictionary \(DocBook\) for multiple LDDs [\#93](https://github.com/NASA-PDS/pds4-information-model/issues/93)
- Stage PDS4 Information Model V1E00 Build\_10b [\#89](https://github.com/NASA-PDS/pds4-information-model/issues/89)

## [v10.1.1](https://github.com/NASA-PDS/pds4-information-model/tree/v10.1.1) (2019-10-19)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v10.1.0...v10.1.1)

**Other closed issues:**

- IMTool: LDDTool Config Properties Update [\#83](https://github.com/NASA-PDS/pds4-information-model/issues/83)
- LDDTool: Update version flag \(-v\) to show IM version as well as software version [\#74](https://github.com/NASA-PDS/pds4-information-model/issues/74)
- LDDTool: IngestLDD 'Report' rules do not make it to Schematron [\#1](https://github.com/NASA-PDS/pds4-information-model/issues/1)

## [v10.1.0](https://github.com/NASA-PDS/pds4-information-model/tree/v10.1.0) (2019-10-11)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/v10.0.0...v10.1.0)

**Defects:**

- LDDTool: XMLSchema Fix Null In Import Cleanup [\#81](https://github.com/NASA-PDS/pds4-information-model/issues/81) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- LDDTool: XMLSchema Fix Nul In Import File Name [\#79](https://github.com/NASA-PDS/pds4-information-model/issues/79) [[high](https://github.com/NASA-PDS/pds4-information-model/labels/high)]
- LDDTool: Fixed the calculation of class extensions and restrictions [\#77](https://github.com/NASA-PDS/pds4-information-model/issues/77) [[low](https://github.com/NASA-PDS/pds4-information-model/labels/low)]
-  CCB-252 Make\_Science\_Facets\_wavelength\_range\_nillable\_BugFix [\#64](https://github.com/NASA-PDS/pds4-information-model/issues/64)

**Other closed issues:**

- Revert CCB-256 Permissible value definitions Ingest LDD [\#75](https://github.com/NASA-PDS/pds4-information-model/issues/75)
- CCB-256 Permissible\_value\_definitions\_Ingest\_LDD\_Update\_Data [\#72](https://github.com/NASA-PDS/pds4-information-model/issues/72)
- CCB-256 Permissible\_value\_definitions\_Ingest\_LDD\_Update [\#70](https://github.com/NASA-PDS/pds4-information-model/issues/70)
- DOMConv\_Depcreation\_FinalCleanup\_Deprecate\_InfoModel\_and\_Associated\_Classes [\#68](https://github.com/NASA-PDS/pds4-information-model/issues/68)
- DOMConv\_Deprecation\_FinalCleanup\_Continued [\#66](https://github.com/NASA-PDS/pds4-information-model/issues/66)

## [v10.0.0](https://github.com/NASA-PDS/pds4-information-model/tree/v10.0.0) (2019-09-12)

[Full Changelog](https://github.com/NASA-PDS/pds4-information-model/compare/f4d66f49a34894ec115bfdfc7370019578b59854...v10.0.0)

**Improvements:**

- DOM Conversion [\#4](https://github.com/NASA-PDS/pds4-information-model/issues/4)

**Other closed issues:**

- DOMConv Deprecation\_FinalCleanup - Fix Errors Found in Build 10a [\#62](https://github.com/NASA-PDS/pds4-information-model/issues/62)
- 190908b\_DOMConv\_Depcreation\_FinalCleanup\_Remove\_DOM\_From\_Filename - Part 1 [\#60](https://github.com/NASA-PDS/pds4-information-model/issues/60)
- 190908\_PDS4\_Information\_Model\_V1D00\_Database\_Update - Part 3 [\#58](https://github.com/NASA-PDS/pds4-information-model/issues/58)
- 190908\_PDS4\_Information\_Model\_V1D00\_Database\_Update - Part 2 [\#56](https://github.com/NASA-PDS/pds4-information-model/issues/56)
- 190908\_PDS4\_Information\_Model\_V1D00\_Database\_Update - Part 1 [\#54](https://github.com/NASA-PDS/pds4-information-model/issues/54)
- DOMConv Deprecation\_FinalCleanup - Deprecate Deprecate InfoModel [\#52](https://github.com/NASA-PDS/pds4-information-model/issues/52)
- DOMConv Deprecation\_FinalCleanup - Deprecate MasterInfoModel [\#50](https://github.com/NASA-PDS/pds4-information-model/issues/50)
- DOMConv Deprecation\_FinalCleanup - Deprecate LDDParser [\#48](https://github.com/NASA-PDS/pds4-information-model/issues/48)
- DOMConv Deprecation\_FinalCleanup - Deprecate GetModels [\#46](https://github.com/NASA-PDS/pds4-information-model/issues/46)
- DOMConv Deprecation\_FinalCleanup - Deprecate MOF Writers [\#44](https://github.com/NASA-PDS/pds4-information-model/issues/44)
- DOMConv Deprecation\_FinalCleanup - ExportModels [\#42](https://github.com/NASA-PDS/pds4-information-model/issues/42)
- CCB-256	Permissible\_value\_definitions\_Ingest\_LDD [\#40](https://github.com/NASA-PDS/pds4-information-model/issues/40)
- DOMConv Deprecation\_FinalCleanup - DeprecatedDefn [\#38](https://github.com/NASA-PDS/pds4-information-model/issues/38)
- DOMConv Deprecation\_FinalCleanup - DOMProtAttr [\#36](https://github.com/NASA-PDS/pds4-information-model/issues/36)
- DOMConv Deprecation\_FinalCleanup - DOMAttr [\#34](https://github.com/NASA-PDS/pds4-information-model/issues/34)
- DOMConv Deprecation\_FinalCleanup WriteDOMSpecification [\#32](https://github.com/NASA-PDS/pds4-information-model/issues/32)
- CCB-249	Schematron Rule Elements - Assert and Report [\#29](https://github.com/NASA-PDS/pds4-information-model/issues/29)
- CCB258 Software\_Updates [\#27](https://github.com/NASA-PDS/pds4-information-model/issues/27)
- CCB-253-Add\_Units\_of\_Force\_as\_a\_unit\_of\_measure [\#25](https://github.com/NASA-PDS/pds4-information-model/issues/25)
- CCB-251 - Add Units of Gmass as a unit of measure [\#23](https://github.com/NASA-PDS/pds4-information-model/issues/23)
- CCB-266-Change\_Data\_Type\_of\_External\_Reference\_reference\_text [\#21](https://github.com/NASA-PDS/pds4-information-model/issues/21)
- CCB-262-Add\_Supporting\_Observationan\_To\_Primary\_Result\_Summary\_purpose [\#19](https://github.com/NASA-PDS/pds4-information-model/issues/19)
- CCB-252\_Make\_Science\_Facets\_wavelength\_range\_nillable [\#17](https://github.com/NASA-PDS/pds4-information-model/issues/17)
- CCB\_250\_Add\_MD5\_To\_Checksum\_Type\_Deprecate\_MD5Deep [\#16](https://github.com/NASA-PDS/pds4-information-model/issues/16)
- CCB\_244\_Deprecate\_Earth-based\_from\_Instrument\_Host\_type [\#14](https://github.com/NASA-PDS/pds4-information-model/issues/14)
- CCB\_254\_Add\_Astrophysical\_To\_Targetype [\#12](https://github.com/NASA-PDS/pds4-information-model/issues/12)
- DOMConv - Install new dd11179.pins file [\#10](https://github.com/NASA-PDS/pds4-information-model/issues/10)
- CCB-237 - Change Attribute doi to Data Type ASCII\_DOI [\#9](https://github.com/NASA-PDS/pds4-information-model/issues/9)
- DOMConv\_UnitTesting\_PINS\_Test\_classOrder [\#7](https://github.com/NASA-PDS/pds4-information-model/issues/7)
- Update Maven docs to refer to Github release assets [\#6](https://github.com/NASA-PDS/pds4-information-model/issues/6)



\* *This Changelog was automatically generated by [github_changelog_generator](https://github.com/github-changelog-generator/github-changelog-generator)*
