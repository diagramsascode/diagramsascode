# diagrams as code
[![Build Status](https://travis-ci.com/diagramsascode/diagramsascode.svg?branch=main)](https://travis-ci.com/diagramsascode/diagramsascode)
[![Gitter](https://badges.gitter.im/diagramsascode/community.svg)](https://gitter.im/diagramsascode/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

Generate valid diagram images from source code.

## How to build a diagram
The following activity diagram has been generated from source code:

![Diagram of an activity diagram](sample_activity_diagram.png)

The full black circle is called *initial node*. It starts the flow.

The black circle with the white circle around it is called *final node*. It ends the flow.

The *actions* Action1, Action2a, Action2b and Action3 are the steps of the flow.

The white diamonds are called *decision node* and *merge node*. They split and merge the flow.

The edges between the nodes are called *control flow*. The define the order of steps.

The outgoing edges of a decision node define the *guard condition* that decides which branch to follow.

