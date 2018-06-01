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

## Graph
\colsbegin
\column{.5\textwidth}

  \begin{tikzpicture}[shorten >=1mm, shorten <=1mm, ->]
    \tikzstyle{vertex}=[circle,fill=black!25,minimum size=17pt,inner sep=0pt]
    \node[vertex] (A) at (1,1) {$A$};
    \node[vertex] (B) at (0,0) {$B$};
    \node[vertex] (C) at (2,0) {$C$};
    \draw (A) -- (B);
    \draw (B) -- (C);
    \draw (A) .. controls +(-55:.4) and +(145:.4) .. (C);
    \draw (C) .. controls +(125:.4) and +(-35:.4) .. (A);
  \end{tikzpicture}

\column{.5\textwidth}

There's nothing here$\vdots$

\colsend

## PageRank Formula
  $$
  PR(X) = (1-d) + d (\frac{PR(T_1)}{C(T_1)} + ... + \frac{PR(T_n)}{C(T_n)})
  $$

## Table

Iteration| $PR(A)$| $PR(B)$| $PR(C)$
:-------:|:----:|:----:|:----:
0|1|1|1
1|1|0.75|1.125
2|1.0625|0.7656|1.1484
$\vdots$|$\vdots$|$\vdots$|$\vdots$|
12|1.0769|0.7692|1.1538

## System of Equations
$$
\begin{vmatrix*}[r]
 & x     & &      &-& 0.5z &=& 0.5 \\
-& 0.25x & &      & &      &=& 0.5 \\
-& 0.25x &-& 0.5y &+& z    &=& 0.5
\end{vmatrix*}
$$

## PageRank
$$
\bordermatrix{  ~ & A   & B & C \cr
                A & 0   & 0 & 1 \cr
                B & 0.5 & 0 & 0 \cr
                C & 0.5 & 1 & 0 \cr}
$$
