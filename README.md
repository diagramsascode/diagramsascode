# diagrams as code
[![Build Status](https://travis-ci.com/diagramsascode/diagramsascode.svg?branch=main)](https://travis-ci.com/diagramsascode/diagramsascode)
[![Gitter](https://badges.gitter.im/diagramsascode/community.svg)](https://gitter.im/diagramsascode/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

Generate valid diagram images from source code.

Get started [here](https://github.com/diagramsascode/diagramsascode/tree/main/image).
 
The diagrams as code project consists of the following sub projects:
* **core**: defines the core modeling elements, like nodes, edges and constraints. Not intended to be used directly. Instead, it needs to be extended for each diagram to be generated.
* **sequence**: builds on core to represent UML sequence diagrams with participants and messages. Adds sequence diagram specific constraints.
* **activity**: builds on core to represent UML activity diagrams with initial/final nodes, actions, decision/merge nodes and control flow. Adds activity diagram specific constraints.
* **image**: enables you to generate automatically layouted images for the diagrams.
 
