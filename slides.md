---
title:          Pagerank mit Markov-Chains
author:
  - Severin Kaderli
  - Marius Schär
date:           2018-MM-dd
lang:           de-CH
links-as notes: true
classoption:
  - aspectratio=169
mainfont:       Helvetica Neue
mathfont:       Latin Modern Math

---
## Material

\LARGE{markov.mariusschaer.ch} ^[Link](https://markov.mariusschaer.ch)^

# Markov Chains

## The PageRank example
\begin{tikzpicture}[shorten >=2mm, shorten <=2mm,-]
  \tikzstyle{vertex}=[circle,fill=black!25,minimum size=17pt,inner sep=0pt]
  \foreach \name/\x/\y in {A/0/2, B/2/2, C/1/1, D/2/0}
    \node[vertex] (\name) at (\x,\y) {$\name$};
  \foreach \from/\to in {A/B,B/C,D/C,A/C,C/A}
    \draw (\from) -- (\to);
\end{tikzpicture}

## Manual
\begin{tikzpicture}[shorten >=1mm, shorten <=1mm, ->]
  \tikzstyle{vertex}=[circle,fill=black!25,minimum size=17pt,inner sep=0pt]
  \node[vertex] (A) at (0,2) {$A$};
  \node[vertex] (B) at (2,2) {$B$};
  \node[vertex] (C) at (1,1) {$C$};
  \node[vertex] (D) at (2,0) {$D$};
  \draw (A) -- (B);
  \draw (B) -- (C);
  \draw (D) -- (C);
  \draw (A) .. controls +(-50:.4) and +(140:.4) .. (C);
  \draw (C) .. controls +(130:.4) and +(-40:.4) .. (A);
\end{tikzpicture}
