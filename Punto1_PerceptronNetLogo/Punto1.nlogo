globals [
  epoch-error
  perceptron
  input-node-1
  input-node-2
]

links-own [ weight ]

turtles-own [activation]

breed [ input-nodes input-node ]
breed [ bias-nodes bias-node ]
breed [ output-nodes output-node ]
output-nodes-own [threshold]

to setup
  clear-all
  ask patches [ set pcolor grey ]

  set-default-shape input-nodes "square"
  set-default-shape bias-nodes "circle"
  set-default-shape output-nodes "circle"

  create-output-nodes 1 [
    set activation random-activation
    set xcor 1
    set size 7
    set threshold 0
    set perceptron self
    set color green
  ]

  create-bias-nodes 1 [
    set activation 1
    setxy 3 7
    set size 1.5
    set color green
    my-create-link-to perceptron
  ]

  create-input-nodes 1 [
    setup-input-nodo
    setxy -6 5
    set input-node-1 self
    set color blue
  ]

  create-input-nodes 1 [
    setup-input-nodo
    setxy -6 0
    set input-node-2 self
    set color orange
  ]

  ask perceptron [ activacion ]
  reset-ticks
end

to setup-input-nodo
    set activation random-activation
    set size 1.5
    my-create-link-to perceptron
end


to my-create-link-to [ anode ]
  create-link-to anode [
    set color violet
    set weight random-float 0.1 - 0.05
    set shape "small-arrow-shape"
  ]
end


to train
  set epoch-error 0
  repeat ejemplos-epoca
  [

    ask input-nodes
      [ set activation random-activation ]


    ask perceptron [
      activacion
      update-weights target-answer
      recolor
    ]
  ]


  set epoch-error epoch-error / ejemplos-epoca
  set epoch-error epoch-error * 0.5
  tick
  plot-error

end


to activacion
  set activation sign sum [ [activation] of end1 * weight ] of my-in-links
  recolor
end

to update-weights [ answer ]
  let output-answer activation
  let output-error answer - output-answer
  set epoch-error epoch-error + (answer - sign output-answer) ^ 2

  ask my-in-links [
    set weight weight + aprendizaje * output-error * [activation] of end1
  ]
end

to-report sign [input]
  ifelse input > threshold
  [ report 1 ]
  [ report -1 ]
end

to-report random-activation
  ifelse random 2 = 0
  [ report 1 ]
  [ report -1 ]
end

to-report target-answer
  let a [activation] of input-node-1 = 1
  let b [activation] of input-node-2 = 1
  report ifelse-value run-result (word "my-" funcion " a b") [1][-1]
end

to-report my-or [a b]
  report (a or b)
end

to-report my-xor [a b]
  report (a xor b)
end

to-report my-and [a b]
  report (a and b)
end

to-report my-nor [a b]
  report not (a or b)
end

to-report my-nand [a b]
  report not (a and b)
end

to recolor
  ifelse activation = 1
    [ set color white ]
    [ set color black ]
  ask in-link-neighbors [ recolor ]
  resize-recolor-links
end


to resize-recolor-links
  ask links [
    ifelse mostrar-pesos
    [ set label precision weight 4 ]
    [ set label "" ]
    set thickness 0.2 * abs weight
    ifelse weight > 0
      [ set color violet ]
      [ set color grey ]
  ]
end


to plot-error
  set-current-plot "Error vs epocas"
  plotxy ticks epoch-error
end



to plot-or
  set-current-plot-pen "positivos"
  plotxy -1 1
  plotxy 1 1
  plotxy 1 -1
  set-current-plot-pen "negativos"
  plotxy -1 -1
end

to plot-xor
  set-current-plot-pen "positivos"
  plotxy -1 1
  plotxy 1 -1
  set-current-plot-pen "negativos"
  plotxy 1 1
  plotxy -1 -1
end

to plot-and
  set-current-plot-pen "positivos"
  plotxy 1 1
  set-current-plot-pen "negativos"
  plotxy 1 -1
  plotxy -1 1
  plotxy -1 -1
end

to plot-nor
  set-current-plot-pen "positivos"
  plotxy -1 -1
  set-current-plot-pen "negativos"
  plotxy 1 1
  plotxy 1 -1
  plotxy -1 1
end

to plot-nand
  set-current-plot-pen "positivos"
  plotxy -1 -1
  plotxy 1 -1
  plotxy -1 1
  set-current-plot-pen "negativos"
  plotxy 1 1
end
