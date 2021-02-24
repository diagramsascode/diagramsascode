# Diagrams as code - image
[![Build Status](https://travis-ci.com/diagramsascode/diagramsascode.svg?branch=main)](https://travis-ci.com/diagramsascode/diagramsascode)
[![Gitter](https://badges.gitter.im/diagramsascode/community.svg)](https://gitter.im/diagramsascode/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

This sub project enables you to generate automatically layouted images for the diagrams.

You can create your own textual representation of a diagram by implementing the [DiagramToSource](https://github.com/diagramsascode/diagramsascode/blob/main/image/src/main/java/org/diagramsascode/image/DiagramToSource.java) 
interface.

To create the source of such a diagram, call `ImageSource.of(diagram, diagramToSource)`.
