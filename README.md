# diagrams as code
[![Build Status](https://travis-ci.com/diagramsascode/diagramsascode.svg?branch=main)](https://travis-ci.com/diagramsascode/diagramsascode)
[![Gitter](https://badges.gitter.im/diagramsascode/community.svg)](https://gitter.im/diagramsascode/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

*Diagrams as code* is a term used for storing the source of a diagram image as a text file.
A generator tool like [PlantUML](https://plantuml.com/) generates an image from the text, and automatically layouts it. According to the [ThoughtWorks Technology Radar](https://www.thoughtworks.com/radar/techniques/diagrams-as-code), a key benefit is that 
you can use version control on the text files.

The diagrams as code project presented here has an additional advantage.

By representing the diagrams as models in Java source code, 
you can automatically check if the generated diagrams are modeled correctly.

Get started [here](https://github.com/diagramsascode/diagramsascode/tree/main/image).
 
# Sub projects
The diagrams as code project consists of the following sub projects:
* **core**: defines the core modeling elements, like nodes, edges and constraints. Not intended to be used directly. Instead, it needs to be extended for each diagram type to be generated.
* **sequence**: builds on core to represent UML sequence diagrams with participants and messages. Adds sequence diagram specific constraints.
* **activity**: builds on core to represent UML activity diagrams with initial/final nodes, actions, decision/merge nodes and control flow. Adds activity diagram specific constraints.
* **image**: enables you to generate automatically layouted images for the diagrams.
 
