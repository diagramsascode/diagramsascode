# diagrams as code
 Generate valid diagram images from source code.
 
 The project consists of the following sub projects:
 * **core**: defines the core modeling elements, like nodes, edges and constraints. Not intended to be used directly. Instead, it needs to be extended for each diagram to be generated.
 * **activity**: builds on core to represent UML activity diagrams with initial/final nodes, actions, decision/merge nodes and control flow. Adds activity diagram specific constraints.
 * **image**: enables you to generate automatically layouted images for the diagrams.
 
